import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Modify extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        ReimbursementRequestDAOImpl requestDAO = new ReimbursementRequestDAOImpl();

        Cookie[] cookies = request.getCookies();
        if(cookies!=null) { // Ensure someone has made an account
            String currentUsername = cookies[0].getValue();
            List<Employee> employees = employeeDAO.findByUsername(currentUsername);
            if(currentUsername != null && !employees.isEmpty()) { // Make sure someone is logged in
                Employee E = employees.get(0);
                if(E.getManager()) { //They are a manager, show manager options
                    Boolean isManager = Boolean.parseBoolean(request.getParameter("is-manager"));
                    String request_id = request.getParameter("request_id");
                    ReimbursementRequest relevantRequest = requestDAO.findById(request_id);
                    Boolean action = Boolean.parseBoolean(request.getParameter("approved"));
                    if(action) { // Approve
                        relevantRequest.setApproved(true);
                        requestDAO.update(relevantRequest);
                        response.sendRedirect("/Project1/AdminRequest");
                    } else { // Reject
                        requestDAO.delete(request_id);
                        request.getRequestDispatcher("/Project1/AdminRequest").include(request, response);
                    }
                } else { //Regular Employee
                    out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                            "You are not a manager, and cannot perform this action.\n" +
                            "</div>");
                }
            } else { // User is not logged in
                request.getRequestDispatcher("/no-user-navbar.html").include(request, response);
                out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                        "  Please log in first.\n" +
                        "</div>");
            }
        } else { // Cookies are null
            request.getRequestDispatcher("/no-user-navbar.html").include(request, response);
            out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                    "  Please log in first.\n" +
                    "</div>");
        }
    }
}
