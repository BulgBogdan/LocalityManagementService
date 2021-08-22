package bulgakov.locality.controller;

import bulgakov.locality.service.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@SessionAttributes(value = "userSession")
public class DeleteLocalityController {

    private LocalityService localityService;
    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public DeleteLocalityController(LocalityService localityService) {
        this.localityService = localityService;
    }

    @GetMapping("/delete/locality")
    public ModelAndView deleteLocality(@RequestParam(name = "localityID") String localId,
                                       @ModelAttribute("userSession") String userSession) {
        localityService.delete(Integer.parseInt(localId));
        boolean confirmDelete = false;
        if (Objects.isNull(localityService.getById(Integer.parseInt(localId)))) {
            confirmDelete = true;
        }
        modelAndView.setViewName("redirect:/locality?nameChairmen=" + userSession + "&confirmDelete=" + confirmDelete);
        return modelAndView;
    }
}
