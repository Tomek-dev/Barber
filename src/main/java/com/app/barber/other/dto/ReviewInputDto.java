package com.app.barber.other.dto;

public class ReviewInputDto {

    private String review;
    private Integer star;

    public ReviewInputDto() {
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}
