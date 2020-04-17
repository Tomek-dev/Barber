package com.app.barber.other.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BarberInputDto {

    @NotBlank
    @Size(min = 4, max = 36)
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotBlank
    private String local;
}
