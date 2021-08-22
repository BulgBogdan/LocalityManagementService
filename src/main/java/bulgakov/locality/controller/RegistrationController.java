package bulgakov.locality.controller;

import bulgakov.locality.entity.Role;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.RoleService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.ChooseResources;
import bulgakov.locality.util.LanguageCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class RegistrationController {

    private UserService userService;
    private RoleService roleService;
    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public RegistrationController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/registration")
    public ModelAndView getRegistration() {
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView postLogin(HttpServletRequest request,
                                  @ModelAttribute("login") String login,
                                  @ModelAttribute("password") String password,
                                  @ModelAttribute("confirmPassword") String confirmPassword,
                                  @ModelAttribute("firstName") String firstName,
                                  @ModelAttribute("lastName") String lastName) {
        setAttributes(login, password, firstName, lastName);
        Role userRole = roleService.getById(2);
        request.getSession().setAttribute("lang", LanguageCheck.getLanguageSession(request));
        if (Objects.nonNull(userService.getByUsername(login))) {
            modelAndView.addObject("loginError", ChooseResources.getMessageResource(
                    LanguageCheck.getLanguageSession(request), "label.loginBusy"));
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        if (!ObjectUtils.isEmpty(password) && password.equals(confirmPassword)) {
            User user = User.builder()
                    .username(login)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .role(userRole)
                    .build();
            userService.create(user);
            request.getSession().setAttribute("userSession", login);
            modelAndView.setViewName("redirect:/home");
        } else {
            modelAndView.addObject("passwordError", ChooseResources.getMessageResource(
                    LanguageCheck.getLanguageSession(request), "label.notConfirmPassword"));
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    private void setAttributes(String login, String password, String firstName, String lastName) {
        if (!ObjectUtils.isEmpty(login)) {
            modelAndView.addObject("login", login);
        }
        if (!ObjectUtils.isEmpty(password)) {
            modelAndView.addObject("password", password);
        }
        if (!ObjectUtils.isEmpty(firstName)) {
            modelAndView.addObject("firstName", firstName);
        }
        if (!ObjectUtils.isEmpty(lastName)) {
            modelAndView.addObject("lastName", lastName);
        }
    }
}
