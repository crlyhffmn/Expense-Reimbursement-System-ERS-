import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewRequestsServlet extends HttpServlet {
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
                        "      <th scope=\"col\">Request ID</th>\n" +
                        "      <th scope=\"col\">Description</th>\n" +
                        "      <th scope=\"col\">Amount (USD)</th>\n" +
                        "      <th scope=\"col\">Approval Status (T/F)</th>\n" +
                        "      <th scope=\"col\">Employee ID</th>\n" +
                        "    </tr>\n" +
                        "  </thead>\n" +
                        "  <tbody>");
                if(E.getManager()) { //They are a manager, show manager options
                    request.getRequestDispatcher("/admin-navbar.html").include(request, response);
                    // List all requests
                    List<ReimbursementRequest> requests = requestDAO.findAll();
                    for(ReimbursementRequest r : requests) {
                        //Output table row
                        out.println("<tr>\n" +
                                "      <td>" + r.getRequestId() + "</td>\n" +
                                "      <td>" + r.getDescription() + "</td>\n" +
                                "      <td>" + r.getAmount() + "</td>\n" +
                                "      <td>" + r.isApproved() + "</td>\n" +
                                "      <td>" + r.getEmployee().getId() + "</td>\n" +
                                "    </tr>");
                    }
                    out.println("</tbody>\n" +
                            "</table>");
                } else {
                    System.out.println("Regular User");
                    request.getRequestDispatcher("/employee-navbar.html").include(request, response);
                    // List their requests
                    List<ReimbursementRequest> requests = requestDAO.findAll();
                    System.out.println("Size: " + requests.size());
                    for(ReimbursementRequest r : requests) {
                        //Output table row
                        if(r.getEmployee().getId() == E.getId()) {
                            out.println("<tr>\n" +
                                    "      <td>" + r.getRequestId() + "</td>\n" +
                                    "      <td>" + r.getDescription() + "</td>\n" +
                                    "      <td>" + r.getAmount() + "</td>\n" +
                                    "      <td>" + r.isApproved() + "</td>\n" +
                                    "      <td>" + r.getEmployee().getId() + "</td>\n" +
                                    "    </tr>");
                        }
                    }
                    out.println("</tbody>\n" +
                            "</table>");
                }
            }
        }
    }
}
