package com.app.barber.other.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReviewInputDto {

    @NotBlank
    private String review;
    @NotNull
    private Integer star;
}
