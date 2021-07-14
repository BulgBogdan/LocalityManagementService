package controller;

import entity.Locality;
import entity.User;
import repository.LocalityDAOImpl;
import repository.UserDAOImpl;
import service.LocalityDAO;
import service.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create/locality")
public class CreateLocalityServlet extends HttpServlet {

    private LocalityDAO localityDAO = new LocalityDAOImpl(Locality.class);

    private UserDAO userDAO = new UserDAOImpl(User.class);

    private String nameChairmen;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        nameChairmen = (String) req.getSession().getAttribute("userSession");
        req.setAttribute("nameChairmen", nameChairmen);
        req.getRequestDispatcher("locality.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String population = req.getParameter("population");
        User user = userDAO.getByUsername(nameChairmen);
        Locality locality = Locality.builder()
                .name(name)
                .population(Integer.parseInt(population))
                .user(user)
                .build();
        localityDAO.create(locality);
        req.setAttribute("nameChairmen", nameChairmen);
        resp.sendRedirect("/locality?nameChairmen=" + nameChairmen + "&confirmCreate=true");
    }
}
