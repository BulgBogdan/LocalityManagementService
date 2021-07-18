package controller;

import entity.Infrastructure;
import repository.InfrastructureDAOImpl;
import service.InfrastructureDAO;
import util.CheckFieldsInfrastructure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit/infrastructure")
public class EditInfrastructureServlet extends HttpServlet {

    private InfrastructureDAO infrastructureDAO = new InfrastructureDAOImpl(Infrastructure.class);

    private String infrastructureID;

    private String cityName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        infrastructureID = req.getParameter("infrastructureID");
        Infrastructure infrastructure = infrastructureDAO.getById(Integer.parseInt(infrastructureID));
        cityName = infrastructure.getLocality().getName();
        req.setAttribute("infrastructure", infrastructure);
        req.setAttribute("infrastructureID", infrastructureID);
        req.setAttribute("cityName", cityName);
        req.getRequestDispatcher("infrastructure.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!CheckFieldsInfrastructure.checkFullFields(req)) {
            req.setAttribute("error", "Заполните все поля");
            CheckFieldsInfrastructure.setAttributeInfrastructure(req);
            req.getRequestDispatcher("infrastructure.jsp").forward(req, resp);
        } else {
            Infrastructure infrastructure = infrastructureDAO.getById(Integer.parseInt(infrastructureID));
            infrastructureDAO.update(CheckFieldsInfrastructure.checkInfrastructure(infrastructure, req));
            req.setAttribute("infrastructure", infrastructure);
            req.setAttribute("cityName", cityName);
            resp.sendRedirect("/infrastructure?cityName=" + cityName + "&confirmEdit=true");
        }
    }
}
