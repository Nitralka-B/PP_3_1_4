package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleServiceInt {
    public List<Role> findAll();
    public void SaveRole(Role role);
    public Role findById(Long id);
}
