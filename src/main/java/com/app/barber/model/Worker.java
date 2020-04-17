package com.app.barber.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imageUrl;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "worker_services",
        joinColumns = @JoinColumn(name = "worker_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Service> services = new HashSet<>();

    @ManyToOne
    private Barber barber;

    @OneToMany(mappedBy = "worker", orphanRemoval = true)
    private Set<Visit> visits = new HashSet<>();

    public void addService(Service service){
        this.services.add(service);
        service.getWorkers().add(this);
    }

    public void removeService(Service service){
        this.services.remove(service);
        service.getWorkers().remove(this);
    }
}
