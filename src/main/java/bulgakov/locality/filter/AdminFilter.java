package bulgakov.locality.filter;

import bulgakov.locality.entity.User;
import bulgakov.locality.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/edit/user", "/delete/user", "/users"})
@Component
public class AdminFilter implements Filter {

    private UserService userService;

    @Autowired
    public AdminFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String userSessionName = (String) session.getAttribute("userSession");
        User user = userService.getByUsername(userSessionName);
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
            isAdmin = true;
        } else {
            isAdmin = false;
        }
        return isAdmin;
    }
}
