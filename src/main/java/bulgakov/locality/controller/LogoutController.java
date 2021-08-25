package bulgakov.locality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(value = "userSession")
public class LogoutController {

    @GetMapping("/logout")
    public ModelAndView getLogout(SessionStatus sessionStatus) {
        ModelAndView modelAndView = new ModelAndView();
        sessionStatus.setComplete();
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
}
