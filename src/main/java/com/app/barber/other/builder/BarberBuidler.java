package com.app.barber.other.builder;

import com.app.barber.model.Barber;
import com.app.barber.model.Review;
import com.app.barber.model.User;

import java.util.Set;

public class BarberBuidler {

    private Barber barber = new Barber();

    public static BarberBuidler buidler(){
        return new BarberBuidler();
    }

    public BarberBuidler rewiews(Set<Review> reviews){
        barber.setReviews(reviews);
        return this;
    }

    public BarberBuidler user(User user){
        barber.setUser(user);
        return this;
    }

    public Barber build(){
        return barber;
    }
}
