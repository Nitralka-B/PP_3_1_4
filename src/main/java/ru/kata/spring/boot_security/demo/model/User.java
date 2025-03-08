package ru.kata.spring.boot_security.demo.model;


import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String FirstName;

    @Column(name = "lastname")
    private String LastName;

    @Column
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {}

    public User(String firstName, String lastName) {
        FirstName = firstName;
        LastName = lastName;
    }

    public User(String firstName, String lastName, Long id) {
        FirstName = firstName;
        LastName = lastName;
        this.id = id;
    }

    public User(String username, String firstName, String lastName, String password) {
        this.username = username;
        FirstName = firstName;
        LastName = lastName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
