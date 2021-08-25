package bulgakov.locality.controller;

import bulgakov.locality.entity.User;
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
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView getLogin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (Objects.nonNull(request.getSession().getAttribute("userSession"))) {
            User user = userService.getByUsername(String.valueOf(request.getSession().getAttribute("userSession")));
            modelAndView.addObject("login", user.getUsername());
            modelAndView.addObject("pass", user.getPassword());
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView postLogin(HttpServletRequest request,
                                  @ModelAttribute("check_username") String login,
                                  @ModelAttribute("check_password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getByUsername(login);
        if (Objects.nonNull(user)) {
            if (user.getPassword().equals(password)) {
                if (StringUtils.isBlank((String) request.getSession().getAttribute("userSession"))) {
                    request.getSession().setAttribute("userSession", login);
                }
                request.getSession().setAttribute("lang", LanguageCheck.getLanguageSession(request));
                modelAndView.setViewName("redirect:/home");
                return modelAndView;
            }
        }
        if (StringUtils.isNotBlank(login)) {
            modelAndView.addObject("login", login);
        }
        modelAndView.addObject("error", ChooseResources.getMessageResource(
                LanguageCheck.getLanguageSession(request), "label.errorLoginPass"));
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
