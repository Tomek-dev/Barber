package com.app.barber.other.dto;

import com.app.barber.other.validation.Time;
import com.app.barber.other.validation.Today;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class VisitInputDto {

    @NotBlank
    @Size(min = 4, max = 36)
    private String name;

    @Time
    @Today
    private String date;
    @NotNull
    private Long service;
    @NotNull
    private Long worker;
}
