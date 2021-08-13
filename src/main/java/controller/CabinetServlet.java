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
            req.setAttribute(
                    "errorOldPass",
                    ChooseResources.getMessageResource(req, "label.errorPass"));
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
            return;
        }
        if (Objects.nonNull(password) && password.equals(confirmPassword)) {
            user.setUsername(user.getUsername());
            user.setPassword(password);
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userDAO.update(user);
            req.setAttribute(
                    "confirmEdit",
                    ChooseResources.getMessageResource(req, "label.confirmEdit"));
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
        } else {
            req.setAttribute(
                    "passwordError",
                    ChooseResources.getMessageResource(req, "label.notConfirmPassword"));
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
        }
    }
}
