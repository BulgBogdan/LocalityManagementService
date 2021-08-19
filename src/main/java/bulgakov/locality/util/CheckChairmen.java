package bulgakov.locality.util;

import bulgakov.locality.entity.User;
import bulgakov.locality.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckChairmen {

    private static UserService userService;

    @Autowired
    public CheckChairmen(UserService userService) {
        this.userService = userService;
    }

    public static boolean isChairmen(String userSession) {
        boolean isChairmen = false;
        User user = userService.getByUsername(userSession);
        if (user.getRole().getId() == 3) {
            isChairmen = true;
        }
        return isChairmen;
    }
}
