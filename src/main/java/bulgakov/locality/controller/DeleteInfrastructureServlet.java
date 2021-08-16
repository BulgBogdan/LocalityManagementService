package bulgakov.locality.controller;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.service.InfrastructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/delete/infrastructure")
@Component
public class DeleteInfrastructureServlet extends HttpServlet {

    private InfrastructureService infrastructureService;

    @Autowired
    public DeleteInfrastructureServlet(InfrastructureService infrastructureService) {
        this.infrastructureService = infrastructureService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String infrastructureID = req.getParameter("infrastructureID");
        Infrastructure infrastructure = infrastructureService.getById(Integer.parseInt(infrastructureID));
        String cityName = infrastructure.getLocality().getName();
        boolean confirmDelete = false;
        infrastructureService.delete(Integer.parseInt(infrastructureID));
        if (Objects.isNull(infrastructureService.getById(Integer.parseInt(infrastructureID)))) {
            confirmDelete = true;
        }
        resp.sendRedirect("/infrastructure?cityName=" + cityName + "&confirmDelete=" + confirmDelete);
    }
}
