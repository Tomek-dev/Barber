package com.app.barber.other.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReviewInputDto {

    @NotBlank
    private String review;
    @NotNull
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
