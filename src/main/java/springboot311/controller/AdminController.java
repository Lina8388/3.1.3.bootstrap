package springboot311.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import springboot311.dao.UserDao;
import springboot311.model.User;
import springboot311.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping()
public class AdminController {

    private UserService userService;
    private UserDao userDao;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @GetMapping(value = "/")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("admin")
    public String getListUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("principal", userService.getUserByName(principal.getName()).getRolesString() );

        model.addAttribute("AllRoles", userService.getListRoles());
        return "users";
    }

    @GetMapping("admin/new")
    public String newUser(Model model,Principal principal) {
        model.addAttribute("AllRoles", userService.getListRoles());
        model.addAttribute("user", new User());
        model.addAttribute("principal", userService.getUserByName(principal.getName()).getRolesString() );
        return "new";
    }

    @PostMapping("admin")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "checked", required = false) String[] checked) {

        user.setRoles(userDao.getRolesByRoleNames(checked));
        userService.add(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "admin/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("AllRoles", userService.getListRoles());
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }


    @PostMapping(value = "admin/update/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id,
                         @RequestParam(value = "checked", required = false) String[] checked) {

        userService.update(id, user, checked);
        return "redirect:/admin";
    }



    @DeleteMapping(value = "admin/remove/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }



}
