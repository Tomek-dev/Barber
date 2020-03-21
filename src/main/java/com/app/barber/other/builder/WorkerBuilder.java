package com.app.barber.other.builder;

import com.app.barber.model.Barber;
import com.app.barber.model.Service;
import com.app.barber.model.Worker;

import java.util.Set;

public class WorkerBuilder {

    private Worker worker = new Worker();

    public static WorkerBuilder builder(){
        return new WorkerBuilder();
    }

    public WorkerBuilder name(String name){
        worker.setName(name);
        return this;
    }

    public WorkerBuilder barber(Barber barber){
        worker.setBarber(barber);
        return this;
    }

    public WorkerBuilder services(Set<Service> services){
        worker.setServices(services);
        return this;
    }

    public Worker build(){
        return worker;
    }
}
