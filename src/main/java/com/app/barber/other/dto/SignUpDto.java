package com.app.barber.other.dto;

import com.app.barber.other.validation.Password;
import com.app.barber.other.validation.PasswordDetails;
import com.app.barber.other.validation.UniqueEmail;
import com.app.barber.other.validation.Username;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Password
public class SignUpDto implements PasswordDetails {

    @Username
    @NotBlank
    @Size(min = 4, max = 24)
    private String username;

    @NotBlank
    @Size(min = 8, max = 36)
    private String password;

    private String confirmPassword;

    @UniqueEmail
    @NotBlank
    @Email
    private String email;

    public SignUpDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
