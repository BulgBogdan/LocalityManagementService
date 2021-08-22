package bulgakov.locality.controller;

import bulgakov.locality.entity.Infrastructure;
import bulgakov.locality.service.InfrastructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class DeleteInfrastructureController {

    private InfrastructureService infrastructureService;
    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public DeleteInfrastructureController(InfrastructureService infrastructureService) {
        this.infrastructureService = infrastructureService;
    }

    @GetMapping("/delete/infrastructure")
    public ModelAndView deleteInfrastructure(@RequestParam(name = "infrastructureID") String infrId) {
        Infrastructure infrastructure = infrastructureService.getById(Integer.parseInt(infrId));
        String cityName = infrastructure.getLocality().getName();
        boolean confirmDelete = false;
        infrastructureService.delete(Integer.parseInt(infrId));
        if (Objects.isNull(infrastructureService.getById(Integer.parseInt(infrId)))) {
            confirmDelete = true;
        }
        modelAndView.setViewName("redirect:/infrastructure?cityName=" + cityName + "&confirmDelete=" + confirmDelete);
        return modelAndView;
    }
}
