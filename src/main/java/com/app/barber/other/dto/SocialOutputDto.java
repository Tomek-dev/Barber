package com.app.barber.other.dto;

import com.app.barber.other.enums.SocialType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SocialOutputDto {

    private Long id;

    private String url;

    private String socialType;
}
