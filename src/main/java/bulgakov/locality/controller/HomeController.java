package bulgakov.locality.controller;

import bulgakov.locality.entity.Locality;
import bulgakov.locality.entity.Role;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.RoleService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.CheckChairmen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes(value = {"lang", "userSession"})
public class HomeController {

    private UserService userService;

    private RoleService roleService;

    private ModelAndView modelAndView = new ModelAndView();

    @Autowired
    public HomeController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    private List<String> getChairmens() {
        Role chairmen = roleService.getById(3);
        return chairmen.getUsers().stream().map(User::getUsername).distinct().collect(Collectors.toList());
    }

    private List<String> getCities(String chairmenName) {
        User chairmen = userService.getByUsername(chairmenName);
        List<String> cities = new ArrayList<>();
        for (Locality locality : chairmen.getLocalities()) {
            cities.add(locality.getName());
        }
        return cities;
    }

    @GetMapping("/home")
    public ModelAndView getHome(@ModelAttribute("userSession") String userSession,
                                @ModelAttribute("lang") String lang,
                                @ModelAttribute("chairmen") String chairmen) {
        modelAndView.addObject("selectLang", lang);
        if (CheckChairmen.isChairmen(userSession)) {
            modelAndView.addObject("isChairmen", true);
            modelAndView.addObject("cities", getCities(userSession));
            modelAndView.addObject("nameChairmen", userSession);
        } else {
            modelAndView.addObject("chairmens", getChairmens());
            if (ObjectUtils.isEmpty(chairmen)) {
                modelAndView.addObject("cities", getCities(getChairmens().get(0)));
            }
        }
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PostMapping("/home")
    public ModelAndView postHome(@ModelAttribute("city") String city,
                                 @ModelAttribute("chairmen") String chairmen,
                                 @ModelAttribute("userSession") String userSession,
                                 @ModelAttribute("lang") String lang) {
        modelAndView.addObject("selectLang", lang);
        modelAndView.addObject("isChairmen", CheckChairmen.isChairmen(userSession));
        if (!ObjectUtils.isEmpty(city)) {
            modelAndView.addObject("nameCity", city);
        } else {
            if (!getCities(chairmen).isEmpty()) {
                modelAndView.addObject("nameCity", getCities(chairmen).get(0));
            }
        }
        if (ObjectUtils.isEmpty(chairmen)) {
            modelAndView.addObject("nameChairmen", getChairmens().get(0));
            modelAndView.addObject("cities", getCities(getChairmens().get(0)));
        } else {
            modelAndView.addObject("cities", getCities(chairmen));
            modelAndView.addObject("nameChairmen", chairmen);
        }
        modelAndView.addObject("chairmens", getChairmens());
        modelAndView.setViewName("home");
        return modelAndView;
    }
}