package com.app.barber.other.dto;

public class VisitInputDto {

    private String name;
    private String date;
    private Long service;
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
