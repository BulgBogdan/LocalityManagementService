package bulgakov.locality.controller;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.service.LocalityService;
import bulgakov.locality.util.CheckChairmen;
import bulgakov.locality.util.CheckConfirmData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/infrastructure")
@Component
public class InfrastructureServlet extends HttpServlet {

    private LocalityService localityService;

    @Autowired
    public InfrastructureServlet(LocalityService localityService) {
        this.localityService = localityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityName = req.getParameter("cityName");
        List<Infrastructure> infrastructures = localityService.getByCityName(cityName).getInfrastructures();
        req.setAttribute("confirmData", CheckConfirmData.getAttributeParam(req));
        req.setAttribute("cityName", cityName);
        req.setAttribute("infrastructures", infrastructures);
        req.setAttribute("isChairmen", CheckChairmen.isChairmen(req.getSession()));
        req.getRequestDispatcher("infrastructure.jsp").forward(req, resp);
    }

}
