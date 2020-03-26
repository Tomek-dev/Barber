package com.app.barber.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "barber", orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "barber", orphanRemoval = true)
    private Set<Worker> workers = new HashSet<>();

    public Barber() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }
}
