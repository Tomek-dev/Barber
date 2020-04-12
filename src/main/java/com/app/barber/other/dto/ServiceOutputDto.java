package com.app.barber.other.dto;

import com.app.barber.model.Worker;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ServiceOutputDto {

    private Long id;

    private Set<String> workers;

    private String name;

    private String description;

    private Double price;

    private Integer time;


    public ServiceOutputDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<String> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers.stream()
                .map(Worker::getName)
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
