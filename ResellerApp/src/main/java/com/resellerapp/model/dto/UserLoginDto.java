package com.resellerapp.model.dto;

import javax.validation.constraints.Size;

public class UserLoginDto {

    @Size(min = 3, max = 20, message = "Length must be between 3 and 20 characters!")
    private String username;
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    private String password;


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
}
