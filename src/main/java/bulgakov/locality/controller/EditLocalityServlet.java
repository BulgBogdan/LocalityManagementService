package bulgakov.locality.controller;

import bulgakov.locality.entity.Locality;
import bulgakov.locality.entity.StatusLocality;
import bulgakov.locality.service.LocalityService;
import bulgakov.locality.service.StatusLocalityService;
import bulgakov.locality.util.CheckLocality;
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

@WebServlet("/edit/locality")
@Component
public class EditLocalityServlet extends HttpServlet {

    private LocalityService localityService;

    private StatusLocalityService statusLocalityService;

    private List<StatusLocality> statusLocalities;

    private String localityID;

    private String userSession;

    @Autowired
    public EditLocalityServlet(LocalityService localityService, StatusLocalityService statusLocalityService) {
        this.localityService = localityService;
        this.statusLocalityService = statusLocalityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        localityID = req.getParameter("localityID");
        statusLocalities = statusLocalityService.getAll();
        userSession = (String) req.getSession().getAttribute("userSession");
        Locality locality = localityService.getById(Integer.parseInt(localityID));
        req.setAttribute("bulgakov/locality", locality);
        req.setAttribute("statusCity", statusLocalities);
        req.setAttribute("chairmenName", userSession);
        req.getRequestDispatcher("locality.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int statusID = Integer.parseInt(req.getParameter("statusLocal"));
        Locality locality = localityService.getById(Integer.parseInt(localityID));
        req.setAttribute("statusCity", statusLocalities);
        req.setAttribute("chairmenName", userSession);
        if (!CheckLocality.checkFullFields(req)) {
            req.setAttribute(
                    "error",
                    ChooseResources.getMessageResource(req, "label.emptyFields"));
            CheckLocality.setAttributeLocality(req);
            req.getRequestDispatcher("locality.jsp").forward(req, resp);
            return;
        } else {
            locality.setStatusLocality(statusLocalityService.getById(statusID));
            localityService.update(locality);
            resp.sendRedirect("/bulgakov/locality" + userSession + "&confirmEdit=true");
        }
    }
}
