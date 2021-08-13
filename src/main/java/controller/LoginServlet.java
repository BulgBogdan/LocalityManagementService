package controller;

import entity.User;
import repository.UserDAOImpl;
import service.UserDAO;
import util.ChooseResources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String sessionUser = (String) session.getAttribute("userSession");
        if (Objects.nonNull(sessionUser)) {
            User user = userDAO.getByUsername(sessionUser);
            req.setAttribute("login", user.getUsername());
            req.setAttribute("pass", user.getPassword());
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("check_username");
        String password = req.getParameter("check_password");
        User user = userDAO.getByUsername(login);
        if (Objects.nonNull(user)) {
            if (user.getPassword().equals(password)) {
                if (Objects.isNull(req.getSession().getAttribute("userSession"))) {
                    req.getSession().setAttribute("userSession", login);
                }
                resp.sendRedirect("home");
                return;
            }
        }
        if (Objects.nonNull(login)) {
            req.setAttribute("login", login);
        }
        req.setAttribute(
                "Error",
                ChooseResources.getMessageResource(req, "label.errorLoginPass"));
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
