package com.app.barber.other.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class AvailableVisitOutputDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time;

    public AvailableVisitOutputDto(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
