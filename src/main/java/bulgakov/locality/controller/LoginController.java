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

import java.util.Objects;

@Controller
@SessionAttributes(value = {"lang", "userSession"})
public class LoginController {

    private UserService userService;

    private ModelAndView modelAndView = new ModelAndView();

    @ModelAttribute("userSession")
    public String userSession() {
        return "";
    }

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(@ModelAttribute("userSession") String userSession) {
        if (!ObjectUtils.isEmpty(userSession)) {
            User user = userService.getByUsername(userSession);
            modelAndView.addObject("login", user.getUsername());
            modelAndView.addObject("pass", user.getPassword());
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@ModelAttribute("check_username") String login,
                                  @ModelAttribute("check_password") String password,
                                  @ModelAttribute("userSession") String userSession,
                                  @ModelAttribute("lang") String lang) {
        User user = userService.getByUsername(login);
        if (Objects.nonNull(user)) {
            if (user.getPassword().equals(password)) {
                if (ObjectUtils.isEmpty(userSession)) {
                    modelAndView.addObject("userSession", login);
                }
                modelAndView.setViewName("redirect:/home");
                return modelAndView;
            }
        }
        if (!ObjectUtils.isEmpty(login)) {
            modelAndView.addObject("login", login);
        }
        modelAndView.addObject(
                "error",
                ChooseResources.getMessageResource(lang, "label.errorLoginPass"));
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
