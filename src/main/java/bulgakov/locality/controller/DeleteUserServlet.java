package bulgakov.locality.controller;

import bulgakov.locality.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/delete/user")
@Component
public class DeleteUserServlet extends HttpServlet {

    private UserService userService;

    @Autowired
    public DeleteUserServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userID = req.getParameter("userID");
        userService.delete(Integer.parseInt(userID));
        boolean confirmDelete = false;
        if (Objects.isNull(userService.getById(Integer.parseInt(userID)))) {
            confirmDelete = true;
        }
        resp.sendRedirect("/users?confirmDelete=" + confirmDelete);
    }
}
