package controller;

import entity.Locality;
import entity.Role;
import entity.User;
import repository.RoleDAOImpl;
import repository.UserDAOImpl;
import service.RoleDAO;
import service.UserDAO;
import util.CheckChairmen;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl(User.class);

    private RoleDAO roleDAO = new RoleDAOImpl(Role.class);

    private List<String> getChairmens() {
        Role chairmen = roleDAO.getById(3);
        return chairmen.getUsers().stream().map(User::getUsername).distinct().collect(Collectors.toList());
    }

    private List<String> getCities(String chairmenName) {
        User chairmen = userDAO.getByUsername(chairmenName);
        List<String> cities = new ArrayList<>();
        for (Locality locality : chairmen.getLocalities()) {
            cities.add(locality.getName());
        }
        return cities;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userSession = (String) req.getSession().getAttribute("userSession");
        if (CheckChairmen.isChairmen(req.getSession())) {
            req.setAttribute("isChairmen", true);
            req.setAttribute("cities", getCities(userSession));
            req.setAttribute("nameChairmen", userSession);
        } else {
            req.setAttribute("chairmens", getChairmens());
            if (Objects.isNull(req.getParameter("chairmen"))) {
                req.setAttribute("cities", getCities(getChairmens().get(0)));
            }
//            else {
//                req.setAttribute("cities", getCities(req.getParameter("chairmen")));
//            }
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        String chairmen = req.getParameter("chairmen");
        req.setAttribute("isChairmen", CheckChairmen.isChairmen(req.getSession()));
        if (Objects.nonNull(city)) {
            req.setAttribute("nameCity", city);
        } else {
            req.setAttribute("nameCity", getCities(chairmen).get(0));
        }
        if (Objects.isNull(chairmen)) {
            req.setAttribute("nameChairmen", getChairmens().get(0));
            req.setAttribute("cities", getCities(getChairmens().get(0)));
        } else {
            req.setAttribute("cities", getCities(chairmen));
            req.setAttribute("nameChairmen", chairmen);
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}