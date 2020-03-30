package com.app.barber.other.dto;

import javax.validation.constraints.NotBlank;

public class WorkerInputDto {

    @NotBlank
    private String name;

    public WorkerInputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
