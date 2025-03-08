package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    private UserService userService;

    @Autowired
    public void setUserSrvice(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value ="/user")
    public  String userPage(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "userPage";
    }

    @GetMapping(value = "/admin")
    public  String adminPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        return "adminPage";
    }

    @PostMapping(value = "/admin/DeleteUser")
    public String deleteUser(@RequestParam("Id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/EditUser")
    public String editUser(@RequestParam("Id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/admin/EditUser")
    public String editUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/addUser")
    public String saveUser(@ModelAttribute User newUser) {
        userService.AddUser(newUser);
        return "redirect:/admin";
    }

}
