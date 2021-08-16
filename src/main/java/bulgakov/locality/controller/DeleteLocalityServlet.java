package bulgakov.locality.controller;

import bulgakov.locality.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/delete/locality")
@Component
public class DeleteLocalityServlet extends HttpServlet {

    private LocalityService localityService;

    @Autowired
    public DeleteLocalityServlet(LocalityService localityService) {
        this.localityService = localityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String localityID = req.getParameter("localityID");
        String userSession = (String) req.getSession().getAttribute("userSession");
        localityService.delete(Integer.parseInt(localityID));
        boolean confirmDelete = false;
        if (Objects.isNull(localityService.getById(Integer.parseInt(localityID)))) {
            confirmDelete = true;
        }
        resp.sendRedirect("/bulgakov/locality" + userSession + "&confirmDelete=" + confirmDelete);
    }
}
