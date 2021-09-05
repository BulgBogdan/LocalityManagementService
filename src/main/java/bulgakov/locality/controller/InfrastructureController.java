package bulgakov.locality.controller;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.entity.Locality;
import bulgakov.locality.service.InfrastructureService;
import bulgakov.locality.service.LocalityService;
import bulgakov.locality.util.CheckChairmen;
import bulgakov.locality.util.CheckConfirmData;
import bulgakov.locality.util.CheckField;
import bulgakov.locality.util.ChooseResources;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes(value = {"lang"})
@RequiredArgsConstructor
public class InfrastructureController {

    private final LocalityService localityService;
    private final InfrastructureService infrastructureService;

    private String cityName;
    private Integer infrastructureID;

    @GetMapping("/infrastructure")
    public ModelAndView getInfrastructure(@RequestParam(name = "cityName") String cityName,
                                          @RequestParam(name = "confData", required = false) String nameParam,
                                          Authentication auth,
                                          @ModelAttribute("lang") String lang) {
        ModelAndView modelAndView = new ModelAndView();
        List<Infrastructure> infrastructures = localityService.getByCityName(cityName).getInfrastructures();
        if (StringUtils.isNotBlank(nameParam)) {
            modelAndView.addObject("confirmData", CheckConfirmData.getAttributeParam(nameParam, lang));
        }
        modelAndView.addObject("cityName", cityName);
        modelAndView.addObject("infrastructures", infrastructures);
        modelAndView.addObject("isChairmen", CheckChairmen.isChairmen(auth.getName()));
        modelAndView.setViewName("infrastructure");
        return modelAndView;
    }

    @GetMapping("/create/infrastructure")
    public ModelAndView getCreateInfrastructure(@RequestParam(name = "cityName") String cityName) {
        ModelAndView modelAndView = new ModelAndView();
        this.cityName = cityName;
        modelAndView.addObject("cityName", cityName);
        modelAndView.addObject("infrastructure", new Infrastructure());
        modelAndView.setViewName("create/infrastructure");
        return modelAndView;
    }

    @PostMapping("/create/infrastructure")
    public ModelAndView postCreateInfrastructure(@ModelAttribute("lang") String lang,
                                                 @ModelAttribute("infrastructure") Infrastructure infrastructure) {
        ModelAndView modelAndView = new ModelAndView();
        Locality locality = localityService.getByCityName(cityName);
        if (!CheckField.checkFullFieldsInfrastructure(infrastructure)) {
            modelAndView.addObject("error",
                    ChooseResources.getMessageResource(lang, "label.emptyFields"));
            modelAndView.addObject("infrastructure", infrastructure);
            modelAndView.addObject("cityName", cityName);
            modelAndView.setViewName("create/infrastructure");
            return modelAndView;
        } else {
            infrastructure.setLocality(locality);
            infrastructureService.create(infrastructure);
        }
        modelAndView.setViewName("redirect:/infrastructure?cityName=" + cityName + "&confData=created");
        return modelAndView;
    }

    @GetMapping("/edit/infrastructure")
    public ModelAndView getEditInfrastructure(@RequestParam(name = "infrastructureID") Integer infrastructureID) {
        ModelAndView modelAndView = new ModelAndView();
        this.infrastructureID = infrastructureID;
        Infrastructure infrastructure = infrastructureService.getById(infrastructureID);
        cityName = infrastructure.getLocality().getName();
        modelAndView.addObject("infrastructure", infrastructure);
        modelAndView.addObject("cityName", cityName);
        modelAndView.setViewName("edit/infrastructure");
        return modelAndView;
    }

    @PostMapping("/edit/infrastructure")
    public ModelAndView postEditInfrastructure(@ModelAttribute("lang") String lang,
                                               @ModelAttribute("infrastructure") Infrastructure editedInfrastructure) {
        ModelAndView modelAndView = new ModelAndView();
        Infrastructure infrastructure = infrastructureService.getById(infrastructureID);
        if (!CheckField.checkFullFieldsInfrastructure(editedInfrastructure)) {
            modelAndView.addObject("error",
                    ChooseResources.getMessageResource(lang, "label.emptyFields"));
            modelAndView.addObject("cityName", cityName);
            modelAndView.setViewName("edit/infrastructure");
        } else {
            infrastructure.setName(editedInfrastructure.getName());
            infrastructure.setFloors(editedInfrastructure.getFloors());
            infrastructure.setPersons(editedInfrastructure.getPersons());
            infrastructure.setSquare(editedInfrastructure.getSquare());
            infrastructureService.update(infrastructure);
            modelAndView.setViewName("redirect:/infrastructure?cityName=" + cityName + "&confData=edited");
        }
        modelAndView.addObject("infrastructure", infrastructure);
        return modelAndView;
    }

    @GetMapping("/delete/infrastructure")
    public ModelAndView deleteInfrastructure(@RequestParam(name = "infrastructureID") Integer infrId) {
        ModelAndView modelAndView = new ModelAndView();
        Infrastructure infrastructure = infrastructureService.getById(infrId);
        String cityName = infrastructure.getLocality().getName();
        infrastructureService.delete(infrId);
        modelAndView.setViewName("redirect:/infrastructure?cityName=" + cityName + "&confData=deleted");
        return modelAndView;
    }
}
