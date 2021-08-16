package bulgakov.locality.controller;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.service.InfrastructureService;
import bulgakov.locality.util.CheckInfrastructure;
import bulgakov.locality.util.ChooseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit/infrastructure")
@Component
public class EditInfrastructureServlet extends HttpServlet {

    private InfrastructureService infrastructureService;

    private String infrastructureID;

    private String cityName;

    @Autowired
    public EditInfrastructureServlet(InfrastructureService infrastructureService) {
        this.infrastructureService = infrastructureService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        infrastructureID = req.getParameter("infrastructureID");
        Infrastructure infrastructure = infrastructureService.getById(Integer.parseInt(infrastructureID));
        cityName = infrastructure.getLocality().getName();
        req.setAttribute("infrastructure", infrastructure);
        req.setAttribute("infrastructureID", infrastructureID);
        req.setAttribute("cityName", cityName);
        req.getRequestDispatcher("infrastructure.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cityName", cityName);
        if (!CheckInfrastructure.checkFullFields(req)) {
            req.setAttribute(
                    "error",
                    ChooseResources.getMessageResource(req, "label.emptyFields"));
            CheckInfrastructure.setAttributeInfrastructure(req);
            req.getRequestDispatcher("infrastructure.jsp").forward(req, resp);
        } else {
            Infrastructure infrastructure = infrastructureService.getById(Integer.parseInt(infrastructureID));
            infrastructureService.update(CheckInfrastructure.checkInfrastructure(infrastructure, req));
            req.setAttribute("infrastructure", infrastructure);
            resp.sendRedirect("/infrastructure?cityName=" + cityName + "&confirmEdit=true");
        }
    }
}
