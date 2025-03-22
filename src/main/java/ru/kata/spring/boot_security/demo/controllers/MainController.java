package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceInt;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceInt;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private UserServiceInt userService;
    private RoleServiceInt roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserSrvice(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value ="/user")
    public  String userPage(Principal principal, Model model) {
        User currentUser = userService.findByUsername(principal.getName());
        model.addAttribute("currentUser", currentUser);
        return "userPage";
    }

    @GetMapping(value = "/admin")
    public  String adminPage(Principal principal, Model model) {
        List<User> users = userService.findAll();
        List<Role> roles = roleService.findAll();
        User currentUser = userService.findByUsername(principal.getName());
        model.addAttribute("allRoles", roles);
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        model.addAttribute("newRole", new Role());
        model.addAttribute("currentUser", currentUser);
        return "adminPage";
    }

    @PostMapping(value = "/admin/DeleteUser")
    public String deleteUser(@RequestParam("Id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

//    @GetMapping(value = "/admin/EditUser")
//    public String editUser(@RequestParam("Id") Long id, Model model) {
//        User userToEdit = userService.findById(id);
//        List<Role> allRoles = roleService.findAll();
//        model.addAttribute("allRoles", allRoles);
//        model.addAttribute("userToEdit", userToEdit);
//        return "editUser";
//    }

    @PostMapping(value = "/admin/EditUser")
    public String editUser(@ModelAttribute User userToEdit, @RequestParam(required = false) String newPassword, @RequestParam("roles") List<Long> roles) {
        userService.update(userToEdit, newPassword, roles);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/addUser")
    public String saveUser(@ModelAttribute User newUser, @RequestParam("roles") List<Long> roleId) {
        Set<Role> roles = roleId.stream().map(roleService::findById).collect(Collectors.toSet());
        newUser.setRoles(roles);
        userService.AddUser(newUser);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/addRole")
    public String addRole(@ModelAttribute Role newRole) {
        roleService.SaveRole(newRole);
        return "redirect:/admin";
    }
}
