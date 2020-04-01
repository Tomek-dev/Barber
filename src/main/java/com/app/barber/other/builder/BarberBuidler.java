package com.app.barber.other.builder;

import com.app.barber.model.*;

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

    public BarberBuidler city(String city){
        barber.setCity(city);
        return this;
    }

    public BarberBuidler local(String local){
        barber.setLocal(local);
        return this;
    }

    public BarberBuidler name(String name){
        barber.setName(name);
        return this;
    }

    public BarberBuidler address(String address){
        barber.setAddress(address);
        return this;
    }

    public BarberBuidler longitude(Double longitude){
        barber.setLongitude(longitude);
        return this;
    }

    public BarberBuidler latitude(Double latitude){
        barber.setLatitude(latitude);
        return this;
    }

    public BarberBuidler workers(Set<Worker> workers){
        barber.setWorkers(workers);
        return this;
    }

    public BarberBuidler open(Open open){
        barber.setOpen(open);
        return this;
    }

    public BarberBuidler visits(Set<Visit> visits){
        barber.setVisits(visits);
        return this;
    }

    public BarberBuidler services(Set<Service> services){
        barber.setServices(services);
        return this;
    }

    public Barber build(){
        return barber;
    }
}
