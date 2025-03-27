package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AdminRestController {
    private UserService userService;
    private RoleService roleService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping(value = "/admin/newUser")
    public void addNewUser(@RequestBody Map<String, Object> payload) {
            User user = new User();
            user.setUsername((String) payload.get("username"));
            user.setFirstName((String) payload.get("firstName"));
            user.setLastName((String) payload.get("lastName"));
            user.setPassword((String) payload.get("password"));

            List<String> roleIdsStr = (List<String>) payload.get("roles");
            Set<Role> roles = roleIdsStr.stream()
                    .map(Long::parseLong)
                    .map(roleService::findById)
                    .collect(Collectors.toSet());

            user.setRoles(roles);
            userService.AddUser(user);
    }

    @GetMapping("/admin/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @DeleteMapping(value = "/admin/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/admin/users")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, Object> payload) {
            Long userId = Long.parseLong(payload.get("id").toString());
            User user = userService.findById(userId);

            user.setUsername((String) payload.get("username"));
            user.setFirstName((String) payload.get("firstName"));
            user.setLastName((String) payload.get("lastName"));

            if (payload.get("password") != null) {
                user.setPassword((String) payload.get("password"));
            }

            List<Long> roleIds = ((List<String>) payload.get("roles")).stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            Set<Role> roles = roleIds.stream()
                    .map(roleService::findById)
                    .collect(Collectors.toSet());
            user.setRoles(roles);

            userService.updateUser(user);
            return ResponseEntity.ok().build();
    }
}
