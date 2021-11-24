import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AddServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

        // Check for unique username
        if(employeeDAO.findByUsername(username).isEmpty()) {
            // create employee
            Employee employee = new Employee();
            employee.setUsername(username);
            employee.setPassword(password);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setManager(false); // Assume they aren't a manager, managers will need to be manually given permissions
            employeeDAO.persist(employee);

            Cookie[] cookies = request.getCookies();
            if(cookies!=null) {
                String currentUsername = cookies[0].getValue();
                if(!currentUsername.equals("") || currentUsername!=null) {
                    List<Employee> employees = employeeDAO.findByUsername(username);
                    Employee E = employees.get(0);
                    if(E.getManager()) { //They are a manager, show manager options
                        request.getRequestDispatcher("admin-navbar.html").include(request, response);
                    }
                } else {
                    request.getRequestDispatcher("no-user-navbar.html").include(request, response);
                }
            }
            request.getRequestDispatcher("no-user-navbar.html").include(request, response);
            out.println("<div class=\"alert alert-success\" role=\"alert\">\n" +
                    "  Employee Account Created.\n" +
                    "</div>");
        } else { //Repeat username
            request.getRequestDispatcher("addUser.html").include(request, response);
            out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                    "That username is taken.\n" +
                    "</div>");
        }
    }

}
