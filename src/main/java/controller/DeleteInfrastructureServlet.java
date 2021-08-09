package controller;

import entity.Infrastructure;
import repository.InfrastructureDAOImpl;
import service.InfrastructureDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/infrastructure")
public class DeleteInfrastructureServlet extends HttpServlet {

    private InfrastructureDAO infrastructureDAO = new InfrastructureDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String infrastructureID = req.getParameter("infrastructureID");
        Infrastructure infrastructure = infrastructureDAO.getById(Integer.parseInt(infrastructureID));
        String cityName = infrastructure.getLocality().getName();
        boolean confirmDelete = infrastructureDAO.delete(Integer.parseInt(infrastructureID));
        resp.sendRedirect("/infrastructure?cityName=" + cityName + "&confirmDelete=" + confirmDelete);
    }
}
