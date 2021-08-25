package bulgakov.locality.controller;

import bulgakov.locality.entity.Role;
import bulgakov.locality.entity.User;
import bulgakov.locality.service.RoleService;
import bulgakov.locality.service.UserService;
import bulgakov.locality.util.CheckConfirmData;
import bulgakov.locality.util.ChooseResources;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes(value = "lang")
public class UsersController {

    private final UserService userService;
    private final RoleService roleService;

    private User user;
    private List<Role> roles;

    @GetMapping("/users")
    public ModelAndView getUsers(@RequestParam(name = "confData", required = false) String nameParam,
                                 @ModelAttribute("lang") String lang) {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.getAll();
        modelAndView.addObject("users", users);
        if (StringUtils.isNotBlank(nameParam)) {
            modelAndView.addObject("confirmData", CheckConfirmData.getAttributeParam(nameParam, lang));
        }
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping("/edit/user")
    public ModelAndView getEditUser(@RequestParam(name = "userID") Integer userID) {
        ModelAndView modelAndView = new ModelAndView();
        user = userService.getById(userID);
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
                                     @ModelAttribute("role") Integer roleId) {
        ModelAndView modelAndView = new ModelAndView();
        Role role = roleService.getById(roleId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        userService.update(user);
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("roleID", roleId);
        modelAndView.addObject("confirmEdit",
                ChooseResources.getMessageResource(lang, "label.confirmEdit"));
        modelAndView.setViewName("/edit/user");
        return modelAndView;
    }

    @GetMapping("/delete/user")
    public ModelAndView deleteUser(@RequestParam(name = "userID") Integer userId) {
        ModelAndView modelAndView = new ModelAndView();
        userService.delete(userId);
        modelAndView.setViewName("redirect:/users?confData=deleted");
        return modelAndView;
    }
}