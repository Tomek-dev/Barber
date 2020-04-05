package com.app.barber.other.dto;

import com.app.barber.other.enums.Star;

import java.time.LocalDateTime;

public class ReviewOutputDto {

    private String review;
    private LocalDateTime date;
    private Star star;
    private String workerName;
    private String serviceName;

    public ReviewOutputDto() {
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
