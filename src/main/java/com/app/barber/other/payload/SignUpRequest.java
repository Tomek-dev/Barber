package com.app.barber.other.payload;

import com.app.barber.other.validation.Password;
import com.app.barber.other.validation.PasswordDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Password
public class SignUpRequest implements PasswordDetails {

    @NotBlank
    @Size(min = 4, max = 24)
    private String username;

    @NotBlank
    @Size(min = 8, max = 36)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)$")
    private String password;

    private String confirmPassword;

    @NotBlank
    @Email
    private String email;

    public SignUpRequest() {
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
