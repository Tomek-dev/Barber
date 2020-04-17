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
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long time;

    @OneToMany(mappedBy = "service", orphanRemoval = true)
    private Set<Visit> visits = new HashSet<>();

    @ManyToOne
    private Barber barber;

    @ManyToMany(mappedBy = "services")
    private Set<Worker> workers = new HashSet<>();
}
