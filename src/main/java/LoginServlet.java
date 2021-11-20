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

        if(employees.isEmpty()) {
            // Error, invalid username
        } else {
            Employee E = employees.get(0);
            String password = request.getParameter("password");
            if(E.getPassword().equals(password)) {
                //Login
            } else {
                //Invalid password
            }
        }

    }
}
