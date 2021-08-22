package bulgakov.locality.controller;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.entity.Locality;
import bulgakov.locality.service.InfrastructureService;
import bulgakov.locality.service.LocalityService;
import bulgakov.locality.util.CheckInfrastructure;
import bulgakov.locality.util.ChooseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes(value = "lang")
public class CreateInfrastructureController {

    private LocalityService localityService;

    private InfrastructureService infrastructureService;

    private String cityName;
    private ModelAndView modelAndView = new ModelAndView();


    @Autowired
    public CreateInfrastructureController(LocalityService localityService, InfrastructureService infrastructureService) {
        this.localityService = localityService;
        this.infrastructureService = infrastructureService;
    }

    @GetMapping("/create/infrastructure")
    public ModelAndView getCreateInfrastructure(@RequestParam(name = "cityName") String cityName) {
        this.cityName = cityName;
        modelAndView.addObject("cityName", cityName);
        modelAndView.setViewName("create/infrastructure");
        return modelAndView;
    }

    @PostMapping("/create/infrastructure")
    public ModelAndView postCreateInfrastructure(@ModelAttribute("lang") String lang,
                                                 HttpServletRequest request) {
        Locality locality = localityService.getByCityName(cityName);
        modelAndView.addObject("cityName", cityName);
        if (!CheckInfrastructure.checkFullFields(request)) {
            modelAndView.addObject("error",
                    ChooseResources.getMessageResource(lang, "label.emptyFields"));

            CheckInfrastructure.setAttributeInfrastructure(request);
            modelAndView.setViewName("create/infrastructure");
            return modelAndView;
        } else {
            Infrastructure infrastructure = CheckInfrastructure.checkInfrastructure(new Infrastructure(), request);
            infrastructure.setLocality(locality);
            infrastructureService.create(infrastructure);
        }
        modelAndView.setViewName("redirect:/infrastructure?cityName=" + cityName + "&confirmCreate=true");
        return modelAndView;
    }
}