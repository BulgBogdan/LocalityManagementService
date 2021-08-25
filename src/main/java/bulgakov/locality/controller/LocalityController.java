package bulgakov.locality.controller;

import bulgakov.locality.entity.Locality;
import bulgakov.locality.entity.StatusLocality;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.LocalityService;
import bulgakov.locality.service.StatusLocalityService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.CheckChairmen;
import bulgakov.locality.util.CheckConfirmData;
import bulgakov.locality.util.CheckField;
import bulgakov.locality.util.ChooseResources;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes(value = {"lang", "userSession"})
@RequiredArgsConstructor
public class LocalityController {

    private final UserService userService;
    private final LocalityService localityService;
    private final StatusLocalityService statusLocalityService;

    private String chairmenName;
    private Integer localityID;
    private List<StatusLocality> statusLocalities;

    @GetMapping("/locality")
    public ModelAndView getLocality(@RequestParam(name = "nameChairmen", required = false) String nameChairmen,
                                    @RequestParam(name = "confData", required = false) String nameParam,
                                    @RequestParam(defaultValue = "1") int page,
                                    @ModelAttribute("userSession") String userSession,
                                    @ModelAttribute("lang") String lang) {
        ModelAndView modelAndView = new ModelAndView();
        if (StringUtils.isNotBlank(nameChairmen)) {
            chairmenName = nameChairmen;
        }
        int pageSize = 5;
        User user;
        if (StringUtils.isNotBlank(chairmenName)) {
            user = userService.getByUsername(chairmenName);
        } else {
            user = userService.getByUsername(userSession);
        }
        Page<Locality> localityServiceLocalities = localityService.getLocalities(user.getId(), page, pageSize);
        List<Locality> localities = localityServiceLocalities.getContent();
        int pagesCount = (user.getLocalities().size() + 4) / 5;
        modelAndView.addObject("page", page);
        modelAndView.addObject("pagesCount", pagesCount);
        if (StringUtils.isNotBlank(nameParam)) {
            modelAndView.addObject("confirmData", CheckConfirmData.getAttributeParam(nameParam, lang));
        }
        modelAndView.addObject("isChairmen", CheckChairmen.isChairmen(userSession));
        modelAndView.addObject("localities", localities);
        modelAndView.addObject("nameChairmen", nameChairmen);
        modelAndView.setViewName("locality");
        return modelAndView;
    }

    @GetMapping("/create/locality")
    public ModelAndView getCreateInfrastructure(@ModelAttribute(name = "userSession") String userSession) {
        ModelAndView modelAndView = new ModelAndView();
        chairmenName = userSession;
        statusLocalities = statusLocalityService.getAll();
        modelAndView.addObject("nameChairmen", chairmenName);
        modelAndView.addObject("locality", new Locality());
        modelAndView.addObject("statusCity", statusLocalities);
        modelAndView.setViewName("/create/locality");
        return modelAndView;
    }

    @PostMapping("/create/locality")
    public ModelAndView postCreateInfrastructure(@ModelAttribute("lang") String lang,
                                                 @ModelAttribute("statusLocal") Integer statusLocal,
                                                 @ModelAttribute("locality") Locality locality) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("statusCity", statusLocalities);
        if (!CheckField.checkFullFieldsLocality(locality)) {
            modelAndView.addObject("error",
                    ChooseResources.getMessageResource(lang, "label.emptyFields"));
            modelAndView.addObject("locality", locality);
            modelAndView.addObject("chairmenName", chairmenName);
            modelAndView.setViewName("/create/locality");
        } else {
            User user = userService.getByUsername(chairmenName);
            locality.setUser(user);
            locality.setStatusLocality(statusLocalityService.getById(statusLocal));
            localityService.create(locality);
            modelAndView.setViewName("redirect:/locality?confData=created");
        }
        return modelAndView;
    }

    @GetMapping("/edit/locality")
    public ModelAndView getEditLocality(@ModelAttribute(name = "userSession") String userSession,
                                        @RequestParam(name = "localityID") Integer localID) {
        ModelAndView modelAndView = new ModelAndView();
        localityID = localID;
        chairmenName = userSession;
        statusLocalities = statusLocalityService.getAll();
        Locality locality = localityService.getById(localID);
        modelAndView.addObject("locality", locality);
        modelAndView.addObject("statusCity", statusLocalities);
        modelAndView.addObject("chairmenName", chairmenName);
        modelAndView.setViewName("edit/locality");
        return modelAndView;
    }

    @PostMapping("/edit/locality")
    public ModelAndView postEditLocality(@ModelAttribute("lang") String lang,
                                         @ModelAttribute("statusLocal") Integer statusLocal,
                                         @ModelAttribute("locality") Locality editedLocality) {
        ModelAndView modelAndView = new ModelAndView();
        Locality locality = localityService.getById(localityID);
        modelAndView.addObject("statusCity", statusLocalities);

        if (!CheckField.checkFullFieldsLocality(editedLocality)) {
            modelAndView.addObject("error",
                    ChooseResources.getMessageResource(lang, "label.emptyFields"));
            modelAndView.addObject("locality", editedLocality);
            modelAndView.addObject("chairmenName", chairmenName);
            modelAndView.setViewName("edit/locality");
        } else {
            locality.setStatusLocality(statusLocalityService.getById(statusLocal));
            locality.setName(editedLocality.getName());
            locality.setPopulation(editedLocality.getPopulation());
            localityService.update(locality);
            modelAndView.setViewName("redirect:/locality?confData=edited");
        }
        return modelAndView;
    }

    @GetMapping("/delete/locality")
    public ModelAndView deleteLocality(@RequestParam(name = "localityID") Integer localId,
                                       @ModelAttribute("userSession") String userSession) {
        ModelAndView modelAndView = new ModelAndView();
        localityService.delete(localId);
        modelAndView.setViewName("redirect:/locality?chairmenName=" + userSession + "&confData=deleted");
        return modelAndView;
    }
}
