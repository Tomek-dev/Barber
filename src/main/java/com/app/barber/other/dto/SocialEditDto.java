package com.app.barber.other.dto;

import javax.validation.constraints.NotBlank;

public class SocialEditDto {

    @NotBlank
    private String url;

    public SocialEditDto(@NotBlank String url) {
        this.url = url;
    }

    public SocialEditDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
