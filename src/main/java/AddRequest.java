import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AddRequest extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            String username = cookies[0].getValue();
            if(!username.equals("") || username!=null) {
                List<Employee> employees = employeeDAO.findByUsername(username);
                Employee E = employees.get(0);
                String description = request.getParameter("description");
                double amount = Double.parseDouble(request.getParameter("amount"));
                ReimbursementRequest reimbursementRequest = new ReimbursementRequest();
                reimbursementRequest.setEmployee(E);
                reimbursementRequest.setDescription(description);
                reimbursementRequest.setAmount(amount);
                reimbursementRequest.setApproved(false);
                reimbursementRequest.setApproved_string("pending");
                ReimbursementRequestDAOImpl requestDAO = new ReimbursementRequestDAOImpl();
                requestDAO.persist(reimbursementRequest);
                request.getRequestDispatcher("/reimbursement-request.html").include(request, response);
                out.println("<div class=\"alert alert-success\" role=\"alert\">\n" +
                        "  Request created. Please wait for a manager to review your request.\n" +
                        "</div>");
            }
        } else {
            out.println("please go to login page and login first");
            request.getRequestDispatcher("/login.html").include(request, response);
        }
    }
}
