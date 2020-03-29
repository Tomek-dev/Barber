package com.app.barber.other.dto;

public class AvailabilityDto {
    private Boolean available;

    public AvailabilityDto(Boolean available) {
        this.available = available;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
