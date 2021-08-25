package bulgakov.locality.controller;

import bulgakov.locality.entity.Role;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.RoleService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.ChooseResources;
import bulgakov.locality.util.LanguageCheck;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/registration")
    public ModelAndView getRegistration() {
        ModelAndView modelAndView = new ModelAndView();
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
        ModelAndView modelAndView = new ModelAndView();
        setAttributes(login, password, firstName, lastName, modelAndView);
        Role userRole = roleService.getById(2);
        request.getSession().setAttribute("lang", LanguageCheck.getLanguageSession(request));
        if (Objects.nonNull(userService.getByUsername(login))) {
            modelAndView.addObject("loginError", ChooseResources.getMessageResource(
                    LanguageCheck.getLanguageSession(request), "label.loginBusy"));
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        if (StringUtils.isNotBlank(password) && password.equals(confirmPassword)) {
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

    private void setAttributes(String login, String password, String firstName, String lastName, ModelAndView mav) {
        if (StringUtils.isNotBlank(login)) {
            mav.addObject("login", login);
        }
        if (StringUtils.isNotBlank(password)) {
            mav.addObject("password", password);
        }
        if (StringUtils.isNotBlank(firstName)) {
            mav.addObject("firstName", firstName);
        }
        if (StringUtils.isNotBlank(lastName)) {
            mav.addObject("lastName", lastName);
        }
    }
}
