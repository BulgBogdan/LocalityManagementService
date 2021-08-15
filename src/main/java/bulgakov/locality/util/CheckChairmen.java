package bulgakov.locality.util;

import bulgakov.locality.entity.User;
import bulgakov.locality.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class CheckChairmen {

    @Autowired
    private static UserService userService;

    public static boolean isChairmen(HttpSession session) {
        boolean isChairmen = false;
        String nameUserSession = (String) session.getAttribute("userSession");
        User user = userService.getByUsername(nameUserSession);
        if (user.getRole().getId() == 3) {
            isChairmen = true;
        }
        return isChairmen;
    }
}
