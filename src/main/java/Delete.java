import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Delete extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        ReimbursementRequestDAOImpl requestDAO = new ReimbursementRequestDAOImpl();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            String username = cookies[0].getValue();
            if(!username.equals("") || username!=null) {
                List<Employee> employees = employeeDAO.findByUsername(username);
                Employee E = employees.get(0);
                out.println("<table class=\"table table-striped table-bordered\">\n" +
                        "  <thead>\n" +
                        "    <tr>\n" +
                        "      <th scope=\"col\">Employee ID</th>\n" +
                        "      <th scope=\"col\">Username</th>\n" +
                        "      <th scope=\"col\">Password</th>\n" +
                        "      <th scope=\"col\">First Name</th>\n" +
                        "      <th scope=\"col\">Last Name</th>\n" +
                        "      <th scope=\"col\">Manager Status</th>\n" +
                        "      <th scope=\"col\">Delete</th>\n" +
                        "    </tr>\n" +
                        "  </thead>\n" +
                        "  <tbody>");
                if(E.getManager()) { //They are a manager, show manager options
                    request.getRequestDispatcher("/admin-navbar.html").include(request, response);
                    // List all employees
                    List<Employee> employeeList = employeeDAO.findAll();
                    for(Employee e : employeeList) {
                        //Output table row
                        out.println("<tr>\n" +
                                "      <td>" + e.getId() + "</td>\n" +
                                "      <td>" + e.getUsername() + "</td>\n" +
                                "      <td>" + e.getPassword() + "</td>\n" +
                                "      <td>" + e.getFirstName() + "</td>\n" +
                                "      <td>" + e.getLastName() + "</td>\n" +
                                "      <td>" + e.getManager() + "</td>\n");
                        if(!e.getUsername().equals(username)) {
                            out.println("      <td><form action=\"DeleteServlet\" method=\"post\"> <input type=\"hidden\" name=\"employee_id\" value=\"" + e.getId() + "\"> <button type=\"submit\" name=\"delete-button\" class=\"btn btn-primary\">Delete</button></form></td>\n");

                        } else {
                            out.println("<td></td>");
                        }
                        out.println("    </tr>");

                    }
                    out.println("</tbody>\n" +
                            "</table>");
                } else {
                    System.out.println("Regular User");
                    request.getRequestDispatcher("/employee-navbar.html").include(request, response);
                    out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                            "You are not an administrator, and cannot access this function.\n" +
                            "</div>");
                    out.println("</tbody>\n" +
                            "</table>");
                }
            }
        }
    }
}
