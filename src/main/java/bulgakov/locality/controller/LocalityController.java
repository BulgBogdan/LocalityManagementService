package bulgakov.locality.controller;

import bulgakov.locality.entity.Locality;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.LocalityService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.CheckChairmen;
import bulgakov.locality.util.CheckConfirmData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
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
    private LocalityService localityService;
    private String nameChair;
    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public LocalityController(UserService userService, LocalityService localityService) {
        this.localityService = localityService;
        this.userService = userService;
    }

    @GetMapping("/locality")
    public ModelAndView getLocality(@RequestParam(name = "nameChairmen", required = false) String nameChairmen,
                                    @RequestParam(name = "editData", required = false) String nameParam,
                                    @RequestParam(defaultValue = "1") int page,
                                    @ModelAttribute("userSession") String userSession,
                                    @ModelAttribute("lang") String lang) {
        nameChair = nameChairmen;
        int pageSize = 5;
        User user = userService.getByUsername(nameChair);
        Page<Locality> localityServiceLocalities = localityService.getLocalities(user.getId(), page, pageSize);
        List<Locality> localities = localityServiceLocalities.getContent();

        int pagesCount = (user.getLocalities().size() + 4) / 5;
        modelAndView.addObject("page", page);
        modelAndView.addObject("pagesCount", pagesCount);
        if (!ObjectUtils.isEmpty(nameParam)) {
            modelAndView.addObject("confirmData", CheckConfirmData.getAttributeParam(nameParam, lang));
        }
        modelAndView.addObject("isChairmen", CheckChairmen.isChairmen(userSession));
        modelAndView.addObject("localities", localities);
        modelAndView.addObject("nameChairmen", nameChairmen);
        modelAndView.setViewName("locality");
        return modelAndView;
    }
}
