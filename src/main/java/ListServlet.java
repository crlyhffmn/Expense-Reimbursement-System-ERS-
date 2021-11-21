import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            String currentUsername = cookies[0].getValue();
            List<Employee> employees = employeeDAO.findByUsername(currentUsername);
            if(!employees.isEmpty()) {
                Employee E = employees.get(0);
                if(E.getManager()) { // Administrator
                    request.getRequestDispatcher("/admin-navbar.html").include(request, response);
                    out.println("<table class=\"table table-striped table-bordered\">\n" +
                            "  <thead>\n" +
                            "    <tr>\n" +
                            "      <th scope=\"col\">Employee ID</th>\n" +
                            "      <th scope=\"col\">Username</th>\n" +
                            "      <th scope=\"col\">Password</th>\n" +
                            "      <th scope=\"col\">First Name</th>\n" +
                            "      <th scope=\"col\">Last Name</th>\n" +
                            "      <th scope=\"col\">Manager Status</th>\n" +
                            "    </tr>\n" +
                            "  </thead>\n" +
                            "  <tbody>");
                    // List employees
                    List<Employee> allEmployees = employeeDAO.findAll();
                    for(Employee e : allEmployees) {
                        //Output table row
                        out.println("<tr>\n" +
                                "      <td>" + e.getId() + "</td>\n" +
                                "      <td>" + e.getUsername() + "</td>\n" +
                                "      <td>" + e.getPassword() + "</td>\n" +
                                "      <td>" + e.getFirstName() + "</td>\n" +
                                "      <td>" + e.getLastName() + "</td>\n" +
                                "      <td>" + e.getManager() + "</td>\n" +
                                "    </tr>");
                    }
                    out.println("</tbody>\n" +
                            "</table>");
                } else { // Normal Employee
                    request.getRequestDispatcher("/employee-navbar.html").include(request, response);
                    out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                            "You are not an administrator, and cannot access this function.\n" +
                            "</div>");
                }
            }
        }
    }
}
