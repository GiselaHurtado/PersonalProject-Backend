package dev.gisela.paddle_tennis_couch_backend.dtos;

public class UserDto {

    private final String username;
    private final String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
