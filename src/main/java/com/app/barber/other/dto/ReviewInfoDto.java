package com.app.barber.other.dto;

public class ReviewInfoDto {

    private Double average;
    private Integer count;

    public ReviewInfoDto(Double average, Integer count) {
        this.average = average;
        this.count = count;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
