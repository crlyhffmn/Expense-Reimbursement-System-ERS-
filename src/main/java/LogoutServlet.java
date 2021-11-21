import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("navbar.html").include(request, response);

        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        out.println("you are logged out successfully");
    }
}
