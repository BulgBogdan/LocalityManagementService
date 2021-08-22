package bulgakov.locality.controller;

import bulgakov.locality.entity.Locality;
import bulgakov.locality.entity.StatusLocality;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.LocalityService;
import bulgakov.locality.service.StatusLocalityService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.CheckLocality;
import bulgakov.locality.util.ChooseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes(value = {"lang", "userSession"})
public class CreateLocalityController {

    private LocalityService localityService;

    private StatusLocalityService statusLocalityService;

    private UserService userService;

    private String nameChairmen;
    private int statusLocal;
    private ModelAndView modelAndView = new ModelAndView();
    private List<StatusLocality> statusLocalities;

    @Autowired
    public CreateLocalityController(LocalityService localityService, StatusLocalityService statusLocalityService, UserService userService) {
        this.localityService = localityService;
        this.statusLocalityService = statusLocalityService;
        this.userService = userService;
    }

    @GetMapping("/create/locality")
    public ModelAndView getCreateInfrastructure(@ModelAttribute(name = "userSession") String userSession,
                                                @RequestParam(name = "statusLocal") String statusLocal) {
        this.nameChairmen = userSession;
        this.statusLocal = Integer.parseInt(statusLocal);
        modelAndView.addObject("nameChairmen", nameChairmen);
        statusLocalities = statusLocalityService.getAll();
        modelAndView.addObject("statusCity", statusLocalities);
        modelAndView.setViewName("/create/locality");
        return modelAndView;
    }

    @PostMapping("/create/locality")
    public ModelAndView postCreateInfrastructure(@ModelAttribute("lang") String lang,
                                                 HttpServletRequest request) {
        modelAndView.addObject("statusCity", statusLocalities);
        modelAndView.addObject("nameChairmen", nameChairmen);
        if (!CheckLocality.checkFullFields(request)) {
            modelAndView.addObject("error", ChooseResources.getMessageResource(
                    lang, "label.emptyFields"));
            CheckLocality.setAttributeLocality(request);
            modelAndView.setViewName("/create/locality");
        } else {
            User user = userService.getByUsername(nameChairmen);
            Locality locality = CheckLocality.checkLocality(new Locality(), request);
            locality.setUser(user);
            locality.setStatusLocality(statusLocalityService.getById(statusLocal));
            localityService.create(locality);
            modelAndView.setViewName("redirect:/locality" + nameChairmen + "&confirmCreate=true");
        }
        return modelAndView;
    }
}
