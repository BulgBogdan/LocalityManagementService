package bulgakov.locality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    private ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/error")
    public ModelAndView getError() {
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
