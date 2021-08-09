package controller;

import entity.User;
import repository.UserDAOImpl;
import service.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("userSession");
        User user = userDAO.getByUsername(login);
        req.setAttribute("user", user);
        if (user.getRole().getId() == 1) {
            req.setAttribute("correctUsers", true);
        }
        req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String oldPass = req.getParameter("oldPassword");
        User user = userDAO.getByUsername(req.getParameter("oldLogin"));
        req.setAttribute("user", user);
        if (!oldPass.equals(user.getPassword())) {
            if (req.getSession().getAttribute("lang").equals("ru")) {
                req.setAttribute("errorOldPass", "Неверный пароль");
            } else {
                req.setAttribute("errorOldPass", "Incorrect password");
            }
            resp.sendRedirect("/cabinet");
            return;
        }
        if (Objects.nonNull(password) && password.equals(confirmPassword)) {
            user.setUsername(user.getUsername());
            user.setPassword(password);
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userDAO.create(user);
            if (req.getSession().getAttribute("lang").equals("ru")) {
                req.setAttribute("confirmEdit", "Данные успешно изменены");
            } else {
                req.setAttribute("confirmEdit", "Data changed successfully");
            }
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
        } else {
            if (req.getSession().getAttribute("lang").equals("ru")) {
                req.setAttribute("passwordError", "Пароли не совпадают");
            } else {
                req.setAttribute("passwordError", "Passwords mismatch");
            }
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
        }
    }
}
