package controller;

import entity.Locality;
import entity.User;
import repository.UserDAOImpl;
import service.UserDAO;
import util.CheckChairmen;
import util.CheckConfirmData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/locality")
public class LocalityServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chairmenParam = req.getParameter("nameChairmen");
        req.setAttribute("confirmData", CheckConfirmData.getAttributeParam(req));
        User user = userDAO.getByUsername(chairmenParam);
        List<Locality> localities = user.getLocalities();
        req.setAttribute("isChairmen", CheckChairmen.isChairmen(req.getSession()));
        req.setAttribute("localities", localities);
        req.getRequestDispatcher("locality.jsp").forward(req, resp);
    }
}
