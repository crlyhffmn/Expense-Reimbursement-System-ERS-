import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

        String username = request.getParameter("username");

        List<Employee> employees = employeeDAO.findByUsername(username);
        System.out.println("Size: " + employees.size());

        if(employees.size() == 0) {
            // Error, invalid username
            System.out.println("Invalid username");
            request.getRequestDispatcher("login.html").include(request, response);
            out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                    "  Invalid Username. Create an Account, or try again.\n" +
                    "</div>");
        } else {
            Employee E = employees.get(0);
            String password = request.getParameter("password");
            if(E.getPassword().equals(password)) {
                //Log in
                System.out.println("Login");
                request.getRequestDispatcher("employee-navbar.html").include(request, response);
            } else {
                //Invalid password
                System.out.println("invalid password");
                request.getRequestDispatcher("login.html").include(request, response);
                out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                        "  Invalid password. Please try again or contact a system administrator.\n" +
                        "</div>");
            }
        }

    }
}
