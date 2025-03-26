package ru.kata.spring.boot_security.demo.DTO;

import ru.kata.spring.boot_security.demo.model.User;

public class UserDTO {
    private String username;
    private Long id;
    private String FirstName;
    private String LastName;
    private String password;
    public UserDTO(User user) {
        this.username = user.getUsername();
        this.id = user.getId();
        this.FirstName = user.getFirstName();
        this.LastName = user.getLastName();
        this.password = user.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
