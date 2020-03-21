package com.app.barber.other.builder;

import com.app.barber.model.Barber;
import com.app.barber.model.Review;
import com.app.barber.other.enums.Star;

import java.time.LocalDateTime;

public class ReviewBuilder {

    private Review review = new Review();

    public static ReviewBuilder builder(){
        return new ReviewBuilder();
    }

    public ReviewBuilder review(String review){
        this.review.setReview(review);
        return this;
    }

    public ReviewBuilder date(LocalDateTime date){
        review.setDate(date);
        return this;
    }

    public ReviewBuilder barber(Barber barber){
        review.setBarber(barber);
        return this;
    }

    public ReviewBuilder star(Star star){
        review.setStar(star);
        return this;
    }

    public Review build(){
        return review;
    }
}
