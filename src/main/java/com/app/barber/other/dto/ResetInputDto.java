package com.app.barber.other.dto;

import com.app.barber.other.validation.Password;
import com.app.barber.other.validation.PasswordDetails;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Password
@Getter
@Setter
public class ResetInputDto implements PasswordDetails {

    @NotBlank
    @Size(min = 8, max = 36)
    private String password;
    private String confirmPassword;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getConfirmPassword() {
        return confirmPassword;
    }
}
