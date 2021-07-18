package controller;

import entity.Infrastructure;
import entity.Locality;
import repository.InfrastructureDAOImpl;
import repository.LocalityDAOImpl;
import service.InfrastructureDAO;
import service.LocalityDAO;
import util.CheckFieldsInfrastructure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create/infrastructure")
public class CreateInfrastructureServlet extends HttpServlet {

    private LocalityDAO localityDAO = new LocalityDAOImpl(Locality.class);

    private InfrastructureDAO infrastructureDAO = new InfrastructureDAOImpl(Infrastructure.class);

    private String cityName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cityName = req.getParameter("cityName");
        req.setAttribute("cityName", cityName);
        req.getRequestDispatcher("infrastructure.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Locality locality = localityDAO.getByCityName(cityName);
        if (!CheckFieldsInfrastructure.checkFullFields(req)) {
            req.setAttribute("error", "Заполните все поля");
            CheckFieldsInfrastructure.setAttributeInfrastructure(req);
            req.getRequestDispatcher("infrastructure.jsp").forward(req, resp);
            return;
        } else {
            Infrastructure infrastructure = CheckFieldsInfrastructure.checkInfrastructure(new Infrastructure(), req);
            infrastructure.setLocality(locality);
            infrastructureDAO.create(infrastructure);
        }
        req.setAttribute("cityName", cityName);
        resp.sendRedirect("/infrastructure?cityName=" + cityName + "&confirmCreate=true");
    }


}
