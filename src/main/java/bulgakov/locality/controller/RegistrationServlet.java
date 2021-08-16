package bulgakov.locality.controller;

import bulgakov.locality.entity.Role;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.RoleService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.ChooseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/registration")
@Component
public class RegistrationServlet extends HttpServlet {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public RegistrationServlet(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        setAttributes(req, login, password, firstName, lastName);
        String confirmPassword = req.getParameter("confirmPassword");
        Role userRole = roleService.getById(2);
        if (Objects.nonNull(userService.getByUsername(login))) {
            req.setAttribute(
                    "loginError",
                    ChooseResources.getMessageResource(req, "label.loginBusy"));
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
            return;
        }
        if (Objects.nonNull(password) && password.equals(confirmPassword)) {
            User user = User.builder()
                    .username(login)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .role(userRole)
                    .build();
            userService.create(user);
            HttpSession session = req.getSession();
            session.setAttribute("userSession", login);
            resp.sendRedirect("/home");
        } else {
            req.setAttribute(
                    "passwordError",
                    ChooseResources.getMessageResource(req, "label.notConfirmPassword"));
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }

    private void setAttributes(HttpServletRequest request,
                               String login, String password, String firstName, String lastName) {
        if (Objects.nonNull(login)) {
            request.setAttribute("login", login);
        }
        if (Objects.nonNull(password)) {
            request.setAttribute("password", password);
        }
        if (Objects.nonNull(firstName)) {
            request.setAttribute("firstName", firstName);
        }
        if (Objects.nonNull(lastName)) {
            request.setAttribute("lastName", lastName);
        }
    }
}
