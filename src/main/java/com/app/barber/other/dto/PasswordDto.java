package com.app.barber.other.dto;

import com.app.barber.other.validation.Password;
import com.app.barber.other.validation.PasswordDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Password
public class PasswordDto implements PasswordDetails {

    @NotBlank
    @Size(min = 8, max = 36)
    private String password;
    private String confirmPassword;

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
}
