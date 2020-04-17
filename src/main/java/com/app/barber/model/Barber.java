package com.app.barber.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Barber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String address;

    private String local;

    private Double longitude;

    private Double latitude;

    @OneToMany(mappedBy = "barber", orphanRemoval = true)
    private Set<Image> images = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "barber", orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "barber", orphanRemoval = true)
    private Set<Worker> workers = new HashSet<>();

    @OneToMany(mappedBy = "barber", orphanRemoval = true)
    private Set<Service> services = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "barber")
    private Set<Open> open = new HashSet<>();

    @OneToMany(mappedBy = "barber", orphanRemoval = true)
    private Set<Visit> visits = new HashSet<>();

    @OneToMany(mappedBy = "barber", orphanRemoval = true)
    private Set<Social> socials = new HashSet<>();

}
