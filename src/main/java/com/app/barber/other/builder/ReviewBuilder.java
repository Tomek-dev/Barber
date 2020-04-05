package com.app.barber.other.builder;

import com.app.barber.model.*;
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

    public ReviewBuilder worker(Worker worker){
        review.setWorker(worker);
        return this;
    }

    public ReviewBuilder service(Service service){
        review.setService(service);
        return this;
    }

    public ReviewBuilder owner(OAuthUser owner){
        review.setOwner(owner);
        return this;
    }

    public Review build(){
        return review;
    }
}
