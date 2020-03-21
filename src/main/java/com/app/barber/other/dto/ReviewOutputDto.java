package com.app.barber.other.dto;

import com.app.barber.other.enums.Star;

import java.time.LocalDateTime;

public class ReviewOutputDto {

    private String review;
    private LocalDateTime date;
    private Star star;

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
}
