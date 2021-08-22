package bulgakov.locality.controller;

import bulgakov.locality.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class DeleteUserController {

    private UserService userService;
    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public DeleteUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/delete/user")
    public ModelAndView deleteUser(@RequestParam(name = "userID") String userId) {
        userService.delete(Integer.parseInt(userId));
        boolean confirmDelete = false;
        if (Objects.isNull(userService.getById(Integer.parseInt(userId)))) {
            confirmDelete = true;
        }
        modelAndView.setViewName("redirect:/users?confirmDelete=" + confirmDelete);
        return modelAndView;
    }
}
