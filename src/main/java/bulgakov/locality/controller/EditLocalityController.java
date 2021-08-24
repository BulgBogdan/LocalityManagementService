package bulgakov.locality.controller;

import bulgakov.locality.entity.Locality;
import bulgakov.locality.entity.StatusLocality;
import bulgakov.locality.service.LocalityService;
import bulgakov.locality.service.StatusLocalityService;
import bulgakov.locality.util.CheckLocality;
import bulgakov.locality.util.ChooseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes(value = "userSession")
public class EditLocalityController extends HttpServlet {

    private LocalityService localityService;
    private StatusLocalityService statusLocalityService;

    private List<StatusLocality> statusLocalities;
    private String localityID;
    private ModelAndView modelAndView = new ModelAndView();
    private String userSession;

    @Autowired
    public EditLocalityController(LocalityService localityService, StatusLocalityService statusLocalityService) {
        this.localityService = localityService;
        this.statusLocalityService = statusLocalityService;
    }

    @GetMapping("/edit/locality")
    public ModelAndView getEditLocality(@ModelAttribute(name = "userSession") String userSession,
                                        @RequestParam(name = "localityID") String localID) {
        localityID = localID;
        this.userSession = userSession;
        statusLocalities = statusLocalityService.getAll();
        Locality locality = localityService.getById(Integer.parseInt(localityID));
        modelAndView.addObject("locality", locality);
        modelAndView.addObject("statusCity", statusLocalities);
        modelAndView.addObject("chairmenName", userSession);
        modelAndView.setViewName("edit/locality");
        return modelAndView;
    }

    @PostMapping("/edit/locality")
    public ModelAndView postEditLocality(@ModelAttribute("lang") String lang,
                                         @ModelAttribute("statusLocal") String statusLocal,
                                         HttpServletRequest request) {
        Locality locality = localityService.getById(Integer.parseInt(localityID));
        modelAndView.addObject("statusCity", statusLocalities);
        modelAndView.addObject("chairmenName", userSession);
        if (!CheckLocality.checkFullFields(request)) {
            modelAndView.addObject("error", ChooseResources.getMessageResource(
                    lang, "label.emptyFields"));
            CheckLocality.setAttributeLocality(request);
            modelAndView.setViewName("edit/locality");
        } else {
            locality.setStatusLocality(statusLocalityService.getById(Integer.parseInt(statusLocal)));
            localityService.update(locality);
            modelAndView.setViewName("redirect:/locality" + userSession + "&confirmEdit=true");
        }
        return modelAndView;
    }
}