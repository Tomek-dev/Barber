package com.app.barber.other.dto;

import com.app.barber.other.validation.Time;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
@Setter
public class OpenInputDto {

    @NotEmpty
    private String day;

    @Time
    @NotEmpty
    private String open;

    @Time
    @NotEmpty
    private String close;
}
