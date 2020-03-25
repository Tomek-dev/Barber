package com.app.barber.other.builder;

import com.app.barber.model.Service;
import com.app.barber.model.Visit;
import com.app.barber.model.Worker;

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

    public Visit build(){
        return visit;
    }
}
