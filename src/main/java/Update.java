import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Update extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            String currentUsername = cookies[0].getValue();
            List<Employee> employees = employeeDAO.findByUsername(currentUsername);
            if((!username.equals("") || currentUsername != null) && !employees.isEmpty()) {
                Employee E = employees.get(0);
                if(E.getManager()) { //They are a manager, show manager options
                    request.getRequestDispatcher("/employeeUpdateManager.html").include(request, response);
                    Boolean isManager = Boolean.parseBoolean(request.getParameter("is-manager"));
                    // Check for unique usernames
                    if(!E.getUsername().equals(username)) {
                        if(employeeDAO.findByUsername(username).isEmpty()) {
                            E.setUsername(username);
                            E.setPassword(password);
                            E.setFirstName(firstName);
                            E.setLastName(lastName);
                            E.setManager(isManager);
                            employeeDAO.update(E);
                            out.println("<div class=\"alert alert-success\" role=\"alert\">\n" +
                                    "Employee update successful.\n" +
                                    "</div>");
                        } else {
                            out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                                    "That username is already taken.\n" +
                                    "</div>");
                        }
                    } else {
                        E.setUsername(username);
                        E.setPassword(password);
                        E.setFirstName(firstName);
                        E.setLastName(lastName);
                        E.setManager(isManager);
                        employeeDAO.update(E);
                        out.println("<div class=\"alert alert-success\" role=\"alert\">\n" +
                                "Employee update successful.\n" +
                                "</div>");
                    }


                } else { //Regular Employee
                    request.getRequestDispatcher("/employeeUpdate.html").include(request, response);
                    // Check for unique usernames
                    if(!E.getUsername().equals(username)) {
                        if(employeeDAO.findByUsername(username).isEmpty()) {
                            E.setUsername(username);
                            E.setPassword(password);
                            E.setFirstName(firstName);
                            E.setLastName(lastName);
                            employeeDAO.update(E);
                            out.println("<div class=\"alert alert-success\" role=\"alert\">\n" +
                                    "Employee update successful.\n" +
                                    "</div>");
                        } else {
                            out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                                    "That username is already taken.\n" +
                                    "</div>");
                        }
                    } else {
                        E.setUsername(username);
                        E.setPassword(password);
                        E.setFirstName(firstName);
                        E.setLastName(lastName);
                        employeeDAO.update(E);
                        out.println("<div class=\"alert alert-success\" role=\"alert\">\n" +
                                "Employee update successful.\n" +
                                "</div>");
                    }
                }
            } else {
                request.getRequestDispatcher("/no-user-navbar.html").include(request, response);
                out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                        "  Please log in first.\n" +
                        "</div>");
            }
        } else {
            request.getRequestDispatcher("/no-user-navbar.html").include(request, response);
            out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                    "  Please log in first.\n" +
                    "</div>");
        }
    }
}
