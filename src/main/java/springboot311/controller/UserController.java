package springboot311.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import springboot311.model.User;
import springboot311.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping()
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "user")
    public String getGeneralPage(Model model, Authentication authentication, Principal principal) {
        model.addAttribute("login",  authentication.getPrincipal());
        model.addAttribute("principal",  userService.getUserByName(principal.getName()).getRolesString());
        return "user";
    }


    @GetMapping(value = "user/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "editUser";
    }


    @PostMapping(value = "user/update/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/user/update";
    }

    @GetMapping("user/update")
    public String getListUsers(Model model,
                               Principal principal) {
        model.addAttribute("login", userService.getUserByName(principal.getName()));
        return "user";
    }

}
