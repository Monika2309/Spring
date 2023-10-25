package com.resellerapp.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterDto {


    @Size(min = 3, max = 20, message = "Length must be between 3 and 20 characters!")
    @NotNull
    private String username;
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    @NotNull
    private String password;
    @Email
    @NotBlank(message = "Email cannot be empty!")
    private String email;
    @NotNull
    private String confirmPassword;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
