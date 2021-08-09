package util;

import entity.User;
import repository.UserDAOImpl;
import service.UserDAO;

import javax.servlet.http.HttpSession;

public class CheckChairmen {

    public static boolean isChairmen(HttpSession session) {
        UserDAO userDAO = new UserDAOImpl();
        boolean isChairmen = false;
        String nameUserSession = (String) session.getAttribute("userSession");
        User user = userDAO.getByUsername(nameUserSession);
        if (user.getRole().getId() == 3) {
            isChairmen = true;
        }
        return isChairmen;
    }
}
