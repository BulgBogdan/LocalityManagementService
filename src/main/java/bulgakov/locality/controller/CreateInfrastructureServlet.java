package bulgakov.locality.controller;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.entity.Locality;
import bulgakov.locality.service.InfrastructureService;
import bulgakov.locality.service.LocalityService;
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

@WebServlet("/create/infrastructure")
@Component
public class CreateInfrastructureServlet extends HttpServlet {

    private LocalityService localityService;

    private InfrastructureService infrastructureService;

    private String cityName;

    @Autowired
    public CreateInfrastructureServlet(LocalityService localityService, InfrastructureService infrastructureService) {
        this.localityService = localityService;
        this.infrastructureService = infrastructureService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cityName = req.getParameter("cityName");
        req.setAttribute("cityName", cityName);
        req.getRequestDispatcher("infrastructure.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Locality locality = localityService.getByCityName(cityName);
        req.setAttribute("cityName", cityName);
        if (!CheckInfrastructure.checkFullFields(req)) {
            req.setAttribute(
                    "error",
                    ChooseResources.getMessageResource(req, "label.emptyFields"));
            CheckInfrastructure.setAttributeInfrastructure(req);
            req.getRequestDispatcher("infrastructure.jsp").forward(req, resp);
            return;
        } else {
            Infrastructure infrastructure = CheckInfrastructure.checkInfrastructure(new Infrastructure(), req);
            infrastructure.setLocality(locality);
            infrastructureService.create(infrastructure);
        }
        resp.sendRedirect("/infrastructure?cityName=" + cityName + "&confirmCreate=true");
    }


}
