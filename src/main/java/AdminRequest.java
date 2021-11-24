import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AdminRequest extends HttpServlet {
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
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<div class=\"table-holder\">");
                stringBuilder.append("<div class=\"table\">");
                stringBuilder.append("<table class=\"table table-striped table-bordered\">\n" +
                        "  <thead>\n" +
                        "    <tr>\n" +
                        "      <th scope=\"col\">Request ID</th>\n" +
                        "      <th scope=\"col\">Description</th>\n" +
                        "      <th scope=\"col\">Amount (USD)</th>\n" +
                        "      <th scope=\"col\">Approval Status</th>\n" +
                        "      <th scope=\"col\">Employee ID</th>\n" +
                        "      <th scope=\"col\">Modify</th>\n" +
                        "    </tr>\n" +
                        "  </thead>\n" +
                        "  <tbody>");
                if(E.getManager()) { //They are a manager, show manager options
                    request.getRequestDispatcher("/admin-navbar.html").include(request, response);
                    out.println(stringBuilder);
                    // List all requests
                    List<ReimbursementRequest> requests = requestDAO.findAll();
                    for(ReimbursementRequest r : requests) {
                        //Output table row
                        out.println("<tr>\n" +
                                "      <td>" + r.getRequestId() + "</td>\n" +
                                "      <td>" + r.getDescription() + "</td>\n" +
                                "      <td>" + r.getAmount() + "</td>\n" +
                                "      <td>" + r.getApproved_string() + "</td>\n" +
                                "      <td>" + r.getEmployee().getId() + "</td>\n");
                        if(!r.getApproved_string().equals("pending")){
                            out.println("<td></td></tr>");
                        } else {
                            out.println("      <td><form action=\"Modify\" method=\"post\"> <input type=\"hidden\" name=\"request_id\" value=\"" + r.getRequestId() + "\"> <div class=\"button-holder\"> <button type=\"submit\" name=\"approved\" class=\"btn btn-custom\" value=\"true\">Accept</button> <button type=\"submit\" name=\"approved\" class=\"btn btn-custom\" value=\"false\">Reject</button></div> </form></td>\n" +
                                    "    </tr>");
                        }
                    }
                    out.println("</tbody>\n" +
                            "</table>");
                    out.println(("</div></div>"));
                } else {
                    System.out.println("Regular User");
                    request.getRequestDispatcher("/employee-navbar.html").include(request, response);
                    out.println(stringBuilder);
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
                                    "      <td>" + r.getApproved_string() + "</td>\n" +
                                    "      <td>" + r.getEmployee().getId() + "</td>\n" +
                                    "    </tr>");
                        }
                    }
                    out.println("</tbody>\n" +
                            "</table>");
                    out.println(("</div></div>"));
                }
            }
        }
    }
}
