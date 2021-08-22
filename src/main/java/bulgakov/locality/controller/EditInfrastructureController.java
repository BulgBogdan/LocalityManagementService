package bulgakov.locality.controller;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.service.InfrastructureService;
import bulgakov.locality.util.CheckInfrastructure;
import bulgakov.locality.util.ChooseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes(value = "lang")
public class EditInfrastructureController {

    private InfrastructureService infrastructureService;

    private String infrastructureID;
    private ModelAndView modelAndView = new ModelAndView();
    private String cityName;

    @Autowired
    public EditInfrastructureController(InfrastructureService infrastructureService) {
        this.infrastructureService = infrastructureService;
    }

    @GetMapping("/edit/infrastructure")
    public ModelAndView getEditInfrastructure(@RequestParam(name = "infrastructureID") String infrastructureID) {
        this.infrastructureID = infrastructureID;
        Infrastructure infrastructure = infrastructureService.getById(Integer.parseInt(infrastructureID));
        cityName = infrastructure.getLocality().getName();
        modelAndView.addObject("infrastructure", infrastructure);
        modelAndView.addObject("cityName", cityName);
        modelAndView.setViewName("edit/infrastructure");
        return modelAndView;
    }

    @PostMapping("/edit/infrastructure")
    public ModelAndView postEditInfrastructure(@ModelAttribute("lang") String lang,
                                               HttpServletRequest request) {
        modelAndView.addObject("cityName", cityName);
        if (!CheckInfrastructure.checkFullFields(request)) {
            modelAndView.addObject("error",
                    ChooseResources.getMessageResource(lang, "label.emptyFields"));
            CheckInfrastructure.setAttributeInfrastructure(request);
            modelAndView.setViewName("edit/infrastructure");
        } else {
            Infrastructure infrastructure = infrastructureService.getById(Integer.parseInt(infrastructureID));
            infrastructureService.update(CheckInfrastructure.checkInfrastructure(infrastructure, request));
            modelAndView.addObject("infrastructure", infrastructure);
            modelAndView.setViewName("redirect:/infrastructure?cityName=" + cityName + "&confirmEdit=true");
        }
        return modelAndView;
    }
}