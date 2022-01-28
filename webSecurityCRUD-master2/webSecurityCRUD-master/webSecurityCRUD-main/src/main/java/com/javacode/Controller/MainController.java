package com.javacode.Controller;


import com.javacode.Model.Role;
import com.javacode.Model.User;
import com.javacode.Service.RoleService;
import com.javacode.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;


@Controller
@RequestMapping(name = "/")
public class MainController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public String listUser(ModelMap modelMap) {
        modelMap.addAttribute("list", userService.getAllUsers());
        return "adminPage";
    }


    @GetMapping(value = "admin/user/{id}")
    String showUserById(@PathVariable(name = "id") long id, Model model) {
        User user = userService.getById(id);

        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "showUserById";

    }

    @GetMapping(value = "admin/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "createNew";
    }


    @PostMapping(value = "/admin/new")
    public String newUser(@ModelAttribute User user,
                          @RequestParam(value = "roless",
                                  required = false,
                                  defaultValue = "ROLE_USER") Set<String> roles) {
        Set<Role> setRoles = roleService.getSetRoles(roles);
        user.setRoles(setRoles);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "/admin/edit/{id}")
    public String editUser(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "editUser";
    }


    @PostMapping(value = "/admin/edit/{id}")
    public String editUser(@ModelAttribute User user,
                           @RequestParam(value = "roless",
                                   required = false,
                                   defaultValue = "ROLE_USER") Set<String> roles) {

        Set<Role> setRoles = roleService.getSetRoles(roles);
        user.setRoles(setRoles);

        if (!user.getPassword().equals(userService.getById(user.getId()).getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        userService.edit(user);

        return "redirect:/admin";
    }


    @GetMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }
}
