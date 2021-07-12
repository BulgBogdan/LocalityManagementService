package controller;

import entity.Locality;
import repository.LocalityDAOImpl;
import service.LocalityDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit/locality")
public class EditLocalityServlet extends HttpServlet {

    private LocalityDAO localityDAO = new LocalityDAOImpl(Locality.class);

    private String localityID;

    private String userSession;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        localityID = req.getParameter("localityID");
        userSession = (String) req.getSession().getAttribute("userSession");
        Locality locality = localityDAO.getById(Integer.parseInt(localityID));
        req.setAttribute("locality", locality);
        req.setAttribute("chairmenName", userSession);
        req.getRequestDispatcher("locality.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String localityName = req.getParameter("name");
        int localityPopulation = Integer.parseInt(req.getParameter("population"));
        Locality locality = localityDAO.getById(Integer.parseInt(localityID));
        locality.setName(localityName);
        locality.setPopulation(localityPopulation);
        localityDAO.update(locality);
        req.setAttribute("chairmenName", userSession);
        req.setAttribute("confirmEdit", "Данные успешно изменены");
        req.getRequestDispatcher("locality.jsp").forward(req, resp);
    }
}
