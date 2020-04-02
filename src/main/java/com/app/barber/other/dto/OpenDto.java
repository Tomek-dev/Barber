package com.app.barber.other.dto;

import javax.validation.constraints.NotEmpty;

public class OpenDto {

    @NotEmpty
    private String day;
    @NotEmpty
    private String open;
    @NotEmpty
    private String close;

    public OpenDto(String open, String close) {
        this.open = open;
        this.close = close;
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
