package controller;

import entity.Locality;
import entity.StatusLocality;
import entity.User;
import repository.LocalityDAOImpl;
import repository.StatusLocalityDAOImpl;
import repository.UserDAOImpl;
import service.LocalityDAO;
import service.StatusLocalityDAO;
import service.UserDAO;
import util.CheckLocality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/create/locality")
public class CreateLocalityServlet extends HttpServlet {

    private LocalityDAO localityDAO = new LocalityDAOImpl(Locality.class);

    private StatusLocalityDAO statusLocalityDAO = new StatusLocalityDAOImpl(StatusLocality.class);

    private UserDAO userDAO = new UserDAOImpl(User.class);

    private String nameChairmen;

    private List<StatusLocality> statusLocalities;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        nameChairmen = (String) req.getSession().getAttribute("userSession");
        req.setAttribute("nameChairmen", nameChairmen);
        statusLocalities = statusLocalityDAO.getAll();
        req.setAttribute("statusCity", statusLocalities);
        req.getRequestDispatcher("locality.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int statusID = Integer.parseInt(req.getParameter("statusLocal"));
        req.setAttribute("statusCity", statusLocalities);
        req.setAttribute("nameChairmen", nameChairmen);
        if (!CheckLocality.checkFullFields(req)) {
            if (req.getSession().getAttribute("lang").equals("ru")) {
                req.setAttribute("error", "Заполните все поля");
            } else {
                req.setAttribute("error", "Put all the fields");
            }
            CheckLocality.setAttributeLocality(req);
            req.getRequestDispatcher("locality.jsp").forward(req, resp);
            return;
        } else {
            User user = userDAO.getByUsername(nameChairmen);
            Locality locality = CheckLocality.checkLocality(new Locality(), req);
            locality.setUser(user);
            locality.setStatusLocality(statusLocalityDAO.getById(statusID));
            localityDAO.create(locality);
            resp.sendRedirect("/locality?nameChairmen=" + nameChairmen + "&confirmCreate=true");
        }
    }
}
