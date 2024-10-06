package dev.gisela.paddle_tennis_couch_backend.dtos;

import java.util.Set;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Set<String> roles;
    private String email;

    public UserDto() {
    }

    public UserDto(Long id, String username, Set<String> roles, String email) {
        this.id = id;  
        this.username = username;
        this.roles = roles;
        this.email = email;
    }

    public UserDto(Long id, String username, String password, Set<String> roles, String email) {
        this.id = id; 
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
