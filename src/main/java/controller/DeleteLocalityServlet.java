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

@WebServlet("/delete/locality")
public class DeleteLocalityServlet extends HttpServlet {

    private LocalityDAO localityDAO = new LocalityDAOImpl(Locality.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String localityID = req.getParameter("localityID");
        Locality locality = localityDAO.getById(Integer.parseInt(localityID));
        String userSession = (String) req.getSession().getAttribute("userSession");
        localityDAO.delete(locality);
        resp.sendRedirect("/locality?nameChairmen=" + userSession + "&confirmDelete=true");
    }
}
