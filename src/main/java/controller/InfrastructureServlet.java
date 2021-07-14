package controller;

import entity.Infrastructure;
import entity.Locality;
import repository.LocalityDAOImpl;
import service.LocalityDAO;
import util.CheckChairmen;
import util.CheckConfirmData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/infrastructure")
public class InfrastructureServlet extends HttpServlet {

    private LocalityDAO localityDAO = new LocalityDAOImpl(Locality.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityName = req.getParameter("cityName");
        List<Infrastructure> infrastructures = localityDAO.getByCityName(cityName).getInfrastructures();
        req.setAttribute("confirmData", CheckConfirmData.getAttributeParam(req));
        req.setAttribute("cityName", cityName);
        req.setAttribute("infrastructures", infrastructures);
        req.setAttribute("isChairmen", CheckChairmen.isChairmen(req.getSession()));
        req.getRequestDispatcher("infrastructure.jsp").forward(req, resp);
    }

}
