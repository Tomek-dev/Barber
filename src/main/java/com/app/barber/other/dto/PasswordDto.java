package com.app.barber.other.dto;

import com.app.barber.other.validation.Password;
import com.app.barber.other.validation.PasswordDetails;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Password
@Getter
@Setter
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

    @Override
    public String getConfirmPassword() {
        return conNewPass;
    }

}
