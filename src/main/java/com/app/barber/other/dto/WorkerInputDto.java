package com.app.barber.other.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WorkerInputDto {

    @NotBlank
    private String name;
}
