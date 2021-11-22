import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DeleteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

        String employee_id = request.getParameter("employee_id");

        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            String currentUsername = cookies[0].getValue();
            List<Employee> employees = employeeDAO.findByUsername(currentUsername);
            if (currentUsername != null && !employees.isEmpty()) {
                Employee E = employees.get(0);
                if (E.getManager()) { //They are a manager
                    ReimbursementRequestDAOImpl requestDAO = new ReimbursementRequestDAOImpl();
                    List<ReimbursementRequest> requests = requestDAO.findAll();
                    for(ReimbursementRequest r : requests) {
                        if(r.getEmployee().getId() == Long.parseLong(employee_id)) {
                            requestDAO.delete(String.valueOf(r.getRequestId()));
                        }
                    }
                    employeeDAO.deleteById(Employee.class, employee_id);
                    response.sendRedirect("Delete");
                } else {
                    request.getRequestDispatcher("/employee-navbar.html").include(request, response);
                    out.println("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                            "You are not an administrator, and cannot access this function.\n" +
                            "</div>");
                }
            }
        }
    }
}
