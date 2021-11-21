import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HomeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            String username = cookies[0].getValue();
            List<Employee> employees = employeeDAO.findByUsername(username);
            System.out.println(employees.isEmpty());
            if((!username.equals("") || username != null) && !employees.isEmpty()) {
                Employee E = employees.get(0);
                if(E.getManager()) { //They are a manager, show manager options
                    request.getRequestDispatcher("/admin-navbar.html").include(request, response);
                } else {
                    request.getRequestDispatcher("/employee-navbar.html").include(request, response);
                }
            } else {
                request.getRequestDispatcher("/no-user-navbar.html").include(request, response);
            }
        } else {
            request.getRequestDispatcher("/no-user-navbar.html").include(request, response);
        }
    }
}
