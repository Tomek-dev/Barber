package com.app.barber.other.payload;

import com.app.barber.other.validation.Password;
import com.app.barber.other.validation.PasswordDetails;
import com.app.barber.other.validation.UniqueEmail;
import com.app.barber.other.validation.Username;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Password
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest implements PasswordDetails {

    @NotBlank
    @Size(min = 4, max = 24)
    @Username
    private String username;

    @NotBlank
    @Size(min = 8, max = 36)
    //@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)")
    private String password;

    private String confirmPassword;

    @NotBlank
    @UniqueEmail
    //@Email(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")
    private String email;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getConfirmPassword() {
        return confirmPassword;
    }
}
