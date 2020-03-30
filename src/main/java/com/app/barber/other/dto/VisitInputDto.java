package com.app.barber.other.dto;

import com.app.barber.other.validation.Today;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VisitInputDto {

    @NotBlank
    @Size(min = 4, max = 36)
    private String name;
    @Today
    private String date;
    @NotNull
    private Long service;
    @NotNull
    private Long worker;

    public VisitInputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getService() {
        return service;
    }

    public void setService(Long service) {
        this.service = service;
    }

    public Long getWorker() {
        return worker;
    }

    public void setWorker(Long worker) {
        this.worker = worker;
    }
}
