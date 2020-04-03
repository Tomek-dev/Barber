package com.app.barber.other.dto;

import com.app.barber.other.validation.Time;

import javax.validation.constraints.NotEmpty;

public class OpenDto {

    @NotEmpty
    private String day;

    @Time
    @NotEmpty
    private String open;

    @Time
    @NotEmpty
    private String close;

    public OpenDto() {
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

    public void setDay(String day) {
        this.day = day;
    }
}
