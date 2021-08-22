package bulgakov.locality.controller;

import bulgakov.locality.entity.User;
import bulgakov.locality.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsersController {

    private UserService userService;
    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {
        List<User> users = userService.getAll();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users");
        return modelAndView;
    }
}