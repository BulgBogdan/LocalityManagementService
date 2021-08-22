package bulgakov.locality.controller;

import bulgakov.locality.entity.Locality;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.CheckChairmen;
import bulgakov.locality.util.CheckConfirmData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes(value = {"lang", "userSession"})
public class LocalityController {

    private UserService userService;

    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public LocalityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/locality")
    public ModelAndView getLocality(@RequestParam(name = "nameChairmen") String nameChairmen,
                                    @RequestParam(name = "editData") String nameParam,
                                    @ModelAttribute("userSession") String userSession,
                                    @ModelAttribute("lang") String lang) {
        User user = userService.getByUsername(nameChairmen);
        List<Locality> localities = user.getLocalities();
        modelAndView.addObject("confirmData", CheckConfirmData.getAttributeParam(nameParam, lang));
        modelAndView.addObject("isChairmen", CheckChairmen.isChairmen(userSession));
        modelAndView.addObject("localities", localities);
        modelAndView.setViewName("locality");
        return modelAndView;
    }
}
