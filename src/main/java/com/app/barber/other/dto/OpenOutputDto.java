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
public class OpenOutputDto {
    private Long id;

    private String day;

    private String open;

    private String close;

    public void setDay(DayOfWeek day) {
        this.day = day.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}
