package filter;

import entity.User;
import repository.UserDAOImpl;
import service.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/edit/user", "/delete/user", "users"})
public class AdminFilter implements Filter {

    private UserDAO userDAO = new UserDAOImpl(User.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String userSessionName = (String) session.getAttribute("userSession");
        User user = userDAO.getByUsername(userSessionName);
        if ((Objects.nonNull(session)) && (session.getAttribute("userSession") != null)) {
            if (isRoleAdmin(user)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("/error");
            }
        } else {
            res.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isRoleAdmin(User user) {
        boolean isAdmin;
        if (user.getRole().getRole().equals("admin")) {
            isAdmin = false;
        } else {
            isAdmin = true;
        }
        return isAdmin;
    }
}
