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
import java.io.IOException;
import java.util.List;

@WebServlet("/edit/user")
@Component
public class EditUserServlet extends HttpServlet {

    private UserService userService;
    private RoleService roleService;

    private User user;

    List<Role> roles;

    @Autowired
    public EditUserServlet(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userID = req.getParameter("userID");
        user = userService.getById(Integer.parseInt(userID));
        roles = roleService.getAll();
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
        Role role = roleService.getById(Integer.parseInt(roleId));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        userService.update(user);
        req.setAttribute("user", user);
        req.setAttribute("roles", roles);
        req.setAttribute("roleID", Integer.parseInt(roleId));
        req.setAttribute(
                "confirmEdit",
                ChooseResources.getMessageResource(req, "label.confirmEdit"));
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}
