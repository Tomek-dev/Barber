package com.app.barber.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "worker", orphanRemoval = true)
    private Set<Service> services;

    @ManyToOne
    private Barber barber;

    public Worker() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }
}
