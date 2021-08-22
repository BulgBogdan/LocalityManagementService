package bulgakov.locality.controller;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.service.LocalityService;
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
public class InfrastructureController {

    private LocalityService localityService;

    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public InfrastructureController(LocalityService localityService) {
        this.localityService = localityService;
    }

    @GetMapping("/infrastructure")
    public ModelAndView getInfrastructure(@RequestParam(name = "cityName") String cityName,
                                          @RequestParam(name = "editData") String nameParam,
                                          @ModelAttribute("userSession") String userSession,
                                          @ModelAttribute("lang") String lang) {
        List<Infrastructure> infrastructures = localityService.getByCityName(cityName).getInfrastructures();
        modelAndView.addObject("confirmData", CheckConfirmData.getAttributeParam(nameParam, lang));
        modelAndView.addObject("cityName", cityName);
        modelAndView.addObject("infrastructures", infrastructures);
        modelAndView.addObject("isChairmen", CheckChairmen.isChairmen(userSession));
        modelAndView.setViewName("infrastructure");
        return modelAndView;
    }
}
