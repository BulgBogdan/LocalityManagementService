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

@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl(User.class);

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
        User user = (User) req.getAttribute("user");
        if (password.equals(confirmPassword)) {
            user.setUsername(user.getUsername());
            user.setPassword(password);
            userDAO.create(user);
            req.setAttribute("confirmEdit", "Данные успешно изменены");
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
        } else {
            req.setAttribute("passwordError", "Пароли не совпадают");
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
        }
    }
}
