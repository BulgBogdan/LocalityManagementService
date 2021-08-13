package controller;

import entity.Role;
import entity.User;
import repository.RoleDAOImpl;
import repository.UserDAOImpl;
import service.RoleDAO;
import service.UserDAO;
import util.ChooseResources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/edit/user")
public class EditUserServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();
    private RoleDAO roleDAO = new RoleDAOImpl();

    private User user;

    List<Role> roles;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userID = req.getParameter("userID");
        user = userDAO.getById(Integer.parseInt(userID));
        roles = roleDAO.getAll();
        req.setAttribute("user", user);
        req.setAttribute("roleID", user.getRole().getId());
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String roleId = req.getParameter("role");
        Role role = roleDAO.getById(Integer.parseInt(roleId));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        userDAO.update(user);
        req.setAttribute("user", user);
        req.setAttribute("roles", roles);
        req.setAttribute("roleID", Integer.parseInt(roleId));
        req.setAttribute(
                "confirmEdit",
                ChooseResources.getMessageResource(req, "label.confirmEdit"));
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}
