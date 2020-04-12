package com.app.barber.other.dto;

import com.app.barber.other.validation.Password;
import com.app.barber.other.validation.PasswordDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Password
public class PasswordDto implements PasswordDetails {

    @NotBlank
    private String oldPass;
    @NotBlank
    @Size(min = 8, max = 36)
    private String newPass;
    private String conNewPass;

    @Override
    public String getPassword() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    @Override
    public String getConfirmPassword() {
        return conNewPass;
    }

    public void setConNewPass(String conNewPass) {
        this.conNewPass = conNewPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public String getConNewPass() {
        return conNewPass;
    }
}
