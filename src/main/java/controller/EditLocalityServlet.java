package controller;

import entity.Locality;
import entity.StatusLocality;
import repository.LocalityDAOImpl;
import repository.StatusLocalityDAOImpl;
import service.LocalityDAO;
import service.StatusLocalityDAO;
import util.CheckLocality;
import util.ChooseResources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/edit/locality")
public class EditLocalityServlet extends HttpServlet {

    private LocalityDAO localityDAO = new LocalityDAOImpl();

    private StatusLocalityDAO statusLocalityDAO = new StatusLocalityDAOImpl();

    private List<StatusLocality> statusLocalities;

    private String localityID;

    private String userSession;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        localityID = req.getParameter("localityID");
        statusLocalities = statusLocalityDAO.getAll();
        userSession = (String) req.getSession().getAttribute("userSession");
        Locality locality = localityDAO.getById(Integer.parseInt(localityID));
        req.setAttribute("locality", locality);
        req.setAttribute("statusCity", statusLocalities);
        req.setAttribute("chairmenName", userSession);
        req.getRequestDispatcher("locality.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int statusID = Integer.parseInt(req.getParameter("statusLocal"));
        Locality locality = localityDAO.getById(Integer.parseInt(localityID));
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
            locality.setStatusLocality(statusLocalityDAO.getById(statusID));
            localityDAO.update(locality);
            resp.sendRedirect("/locality?nameChairmen=" + userSession + "&confirmEdit=true");
        }
    }
}
