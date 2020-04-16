package com.app.barber.other.dto;

import com.app.barber.other.enums.Star;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ReviewOutputDto {

    private String review;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyyy", locale = "English")
    private LocalDateTime date;
    private Integer star;
    private String workerName;
    private String serviceName;
    private String ownerName;
    private String ownerImageUrl;
    private Long id;

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

    public Integer getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star.getNumber();
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerImageUrl() {
        return ownerImageUrl;
    }

    public void setOwnerImageUrl(String ownerImageUrl) {
        this.ownerImageUrl = ownerImageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
