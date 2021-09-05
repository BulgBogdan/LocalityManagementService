package bulgakov.locality.controller;

import bulgakov.locality.entity.Role;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.RoleService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.ChooseResources;
import bulgakov.locality.util.LanguageCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/registration")
    public ModelAndView getRegistration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView postLogin(HttpServletRequest request,
                                  @ModelAttribute("user") User user,
                                  @ModelAttribute("confirmPassword") String confirmPassword) {
        ModelAndView modelAndView = new ModelAndView();
        Role userRole = roleService.getById(2);
        user.setRole(userRole);
        request.getSession().setAttribute("lang", LanguageCheck.getLanguageSession(request));
        if (!user.getPassword().equals(confirmPassword)) {
            modelAndView.addObject("passwordError", ChooseResources.getMessageResource(
                    LanguageCheck.getLanguageSession(request), "label.notConfirmPassword"));
            return modelAndView;
        }
        if (!userService.create(user)) {
            modelAndView.addObject("loginError", ChooseResources.getMessageResource(
                    LanguageCheck.getLanguageSession(request), "label.loginBusy"));
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
}
