package bulgakov.locality.controller;

import bulgakov.locality.entity.Locality;
import bulgakov.locality.entity.StatusLocality;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.LocalityService;
import bulgakov.locality.service.StatusLocalityService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.CheckLocality;
import bulgakov.locality.util.ChooseResources;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/create/locality")
@Component
public class CreateLocalityServlet extends HttpServlet {

    private LocalityService localityService;

    private StatusLocalityService statusLocalityService;

    private UserService userService;

    private String nameChairmen;

    private List<StatusLocality> statusLocalities;

    public CreateLocalityServlet(LocalityService localityService, StatusLocalityService statusLocalityService, UserService userService) {
        this.localityService = localityService;
        this.statusLocalityService = statusLocalityService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        nameChairmen = (String) req.getSession().getAttribute("userSession");
        req.setAttribute("nameChairmen", nameChairmen);
        statusLocalities = statusLocalityService.getAll();
        req.setAttribute("statusCity", statusLocalities);
        req.getRequestDispatcher("locality.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int statusID = Integer.parseInt(req.getParameter("statusLocal"));
        req.setAttribute("statusCity", statusLocalities);
        req.setAttribute("nameChairmen", nameChairmen);
        if (!CheckLocality.checkFullFields(req)) {
            req.setAttribute(
                    "error",
                    ChooseResources.getMessageResource(req, "label.emptyFields"));
            CheckLocality.setAttributeLocality(req);
            req.getRequestDispatcher("locality.jsp").forward(req, resp);
            return;
        } else {
            User user = userService.getByUsername(nameChairmen);
            Locality locality = CheckLocality.checkLocality(new Locality(), req);
            locality.setUser(user);
            locality.setStatusLocality(statusLocalityService.getById(statusID));
            localityService.create(locality);
            resp.sendRedirect("/bulgakov/locality" + nameChairmen + "&confirmCreate=true");
        }
    }
}
