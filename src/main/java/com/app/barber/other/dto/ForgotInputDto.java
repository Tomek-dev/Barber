package com.app.barber.other.dto;

import javax.validation.constraints.NotBlank;

public class ForgotInputDto {
    @NotBlank
    private String username;

    public ForgotInputDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
