package com.app.barber.other.dto;

import com.app.barber.other.validation.Time;

import javax.validation.constraints.NotEmpty;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class OpenOutputDto {
    private Long id;

    private String day;

    private String open;

    private String close;

    public OpenOutputDto() {
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getDay() {
        return day;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDay(DayOfWeek day) {
        this.day = day.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}
