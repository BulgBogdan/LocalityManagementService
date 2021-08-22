package bulgakov.locality.controller;

import bulgakov.locality.entity.User;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.ChooseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(value = {"lang", "userSession"})
public class CabinetController {

    private UserService userService;

    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public CabinetController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/cabinet")
    public ModelAndView getCabinet(@ModelAttribute("userSession") String userSession) {
        User user = userService.getByUsername(userSession);
        modelAndView.addObject("user", user);
        if (user.getRole().getId() == 1) {
            modelAndView.addObject("correctUsers", true);
        }
        modelAndView.setViewName("cabinet");
        return modelAndView;
    }

    @PostMapping("/cabinet")
    public ModelAndView postCabinet(@ModelAttribute("oldLogin") String login,
                                    @ModelAttribute("password") String password,
                                    @ModelAttribute("confirmPassword") String confirmPassword,
                                    @ModelAttribute("oldPassword") String oldPassword,
                                    @ModelAttribute("firstName") String firstName,
                                    @ModelAttribute("lastName") String lastName,
                                    @ModelAttribute("lang") String lang) {
        User user = userService.getByUsername(login);
        modelAndView.addObject("user", user);
        if (!oldPassword.equals(user.getPassword())) {
            modelAndView.addObject("errorOldPass",
                    ChooseResources.getMessageResource(lang, "label.errorPass"));
            modelAndView.setViewName("cabinet");
            return modelAndView;
        }
        if (!ObjectUtils.isEmpty(password) && password.equals(confirmPassword)) {
            user.setUsername(user.getUsername());
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userService.update(user);
            modelAndView.addObject("confirmEdit",
                    ChooseResources.getMessageResource(lang, "label.confirmEdit"));
            modelAndView.setViewName("cabinet");
        } else {
            modelAndView.addObject("passwordError",
                    ChooseResources.getMessageResource(lang, "label.notConfirmPassword"));
            modelAndView.setViewName("cabinet");
        }
        return modelAndView;
    }

}