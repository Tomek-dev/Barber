package com.app.barber.other.builder;

import com.app.barber.model.Service;
import com.app.barber.model.Visit;
import com.app.barber.model.Worker;

import java.util.Set;

public class ServiceBuilder {

    private Service service = new Service();

    public static ServiceBuilder builder(){
        return new ServiceBuilder();
    }

    public ServiceBuilder name(String name){
        service.setName(name);
        return this;
    }

    public ServiceBuilder description(String description){
        service.setDescription(description);
        return this;
    }

    public ServiceBuilder price(Double price){
        service.setPrice(price);
        return this;
    }

    public ServiceBuilder worker(Worker worker){
        service.setWorker(worker);
        return this;
    }

    public ServiceBuilder visits(Set<Visit> visits){
        service.setVisits(visits);
        return this;
    }

    public ServiceBuilder time(Long time){
        service.setTime(time);
        return this;
    }

    public Service build(){
        return service;
    }
}
