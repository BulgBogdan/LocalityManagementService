package controller;

import entity.Role;
import entity.User;
import repository.RoleDAOImpl;
import repository.UserDAOImpl;
import service.RoleDAO;
import service.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl(User.class);
    private RoleDAO roleDAO = new RoleDAOImpl(Role.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        Role userRole = roleDAO.getById(2);
        if (Objects.nonNull(userDAO.getByUsername(login))) {
            req.setAttribute("loginError", "Логин уже занят");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
            return;
        }
        if (password.equals(confirmPassword)) {
            User user = User.builder()
                    .username(login)
                    .password(password)
                    .role(userRole)
                    .build();
            userDAO.create(user);
            HttpSession session = req.getSession();
            session.setAttribute("userSession", login);
            resp.sendRedirect("/home");
        } else {
            req.setAttribute("passwordError", "Пароли не совпадают");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }
}
