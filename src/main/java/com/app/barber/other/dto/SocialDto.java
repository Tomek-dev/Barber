package com.app.barber.other.dto;

import com.app.barber.other.enums.SocialType;

import javax.validation.constraints.NotBlank;

public class SocialDto {

    @NotBlank
    private String url;

    @NotBlank
    private String socialType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(SocialType socialType) {
        this.socialType = socialType.getValue();
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }
}
