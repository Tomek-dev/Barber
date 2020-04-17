package com.app.barber.other.dto;

import com.app.barber.other.enums.SocialType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SocialInputDto {

    @NotBlank
    private String url;

    @NotBlank
    private String socialType;
}
