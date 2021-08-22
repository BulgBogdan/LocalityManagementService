package bulgakov.locality.controller;

import bulgakov.locality.entity.Role;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.RoleService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.ChooseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes(value = "lang")
public class EditUserController {

    private UserService userService;
    private RoleService roleService;

    private User user;
    private ModelAndView modelAndView = new ModelAndView();
    private List<Role> roles;

    @Autowired
    public EditUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/edit/user")
    public ModelAndView getEditUser(@RequestParam(name = "userID") String userID) {
        user = userService.getById(Integer.parseInt(userID));
        roles = roleService.getAll();
        modelAndView.addObject("user", user);
        modelAndView.addObject("roleID", user.getRole().getId());
        modelAndView.addObject("roles", roles);
        modelAndView.setViewName("/edit/user");
        return modelAndView;
    }

    @PostMapping("/edit/user")
    public ModelAndView postEditUser(@ModelAttribute("lang") String lang,
                                     @ModelAttribute("firstName") String firstName,
                                     @ModelAttribute("lastName") String lastName,
                                     @ModelAttribute("role") String roleId) {
        Role role = roleService.getById(Integer.parseInt(roleId));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        userService.update(user);
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("roleID", Integer.parseInt(roleId));
        modelAndView.addObject("confirmEdit", ChooseResources.getMessageResource(
                lang, "label.confirmEdit"));
        modelAndView.setViewName("/edit/user");
        return modelAndView;
    }
}