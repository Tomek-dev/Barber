package com.app.barber.other.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ReportDto {

    @NotEmpty
    private Integer reason;
}
