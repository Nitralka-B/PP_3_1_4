package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServiceInt {
    public User findByUsername(String username);
    public List<User> findAll();
    public User save(User user);
    public void delete(Long id);
    public User findById(Long id);
    public void update(User user, String newPassword, List<Long> roles);
    public void AddUser(User user);
    public void updateUser(User user);
    public User createUserFromDto(UserDto userDto);
    public User updateUserFromDto(UserDto userDto);
}
