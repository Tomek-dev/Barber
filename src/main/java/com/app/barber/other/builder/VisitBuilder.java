package com.app.barber.other.builder;

import com.app.barber.model.*;

import java.time.LocalDateTime;

public class VisitBuilder {

    private Visit visit = new Visit();

    public static VisitBuilder builder(){
        return new VisitBuilder();
    }

    public VisitBuilder name(String name){
        visit.setName(name);
        return this;
    }

    public VisitBuilder worker(Worker worker){
        visit.setWorker(worker);
        return this;
    }

    public VisitBuilder service(Service service){
        visit.setService(service);
        return this;
    }

    public VisitBuilder beginning(LocalDateTime beginning){
        visit.setBeginning(beginning);
        return this;
    }

    public VisitBuilder finish(LocalDateTime finish){
        visit.setFinish(finish);
        return this;
    }

    public VisitBuilder barber(Barber barber){
        visit.setBarber(barber);
        return this;
    }

    public VisitBuilder customer(OAuthUser customer){
        visit.setCustomer(customer);
        return this;
    }

    public Visit build(){
        return visit;
    }
}
