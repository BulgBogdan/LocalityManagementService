package bulgakov.locality.controller;

import bulgakov.locality.entity.Locality;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.CheckChairmen;
import bulgakov.locality.util.CheckConfirmData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bulgakov/locality")
@Component
public class LocalityServlet extends HttpServlet {

    private UserService userService;

    @Autowired
    public LocalityServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chairmenParam = req.getParameter("nameChairmen");
        req.setAttribute("confirmData", CheckConfirmData.getAttributeParam(req));
        User user = userService.getByUsername(chairmenParam);
        List<Locality> localities = user.getLocalities();
        req.setAttribute("isChairmen", CheckChairmen.isChairmen(req.getSession()));
        req.setAttribute("localities", localities);
        req.getRequestDispatcher("locality.jsp").forward(req, resp);
    }
}
