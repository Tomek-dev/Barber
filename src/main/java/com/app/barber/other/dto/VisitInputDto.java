package com.app.barber.other.dto;

public class VisitInputDto {

    private String name;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minutes;
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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
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
