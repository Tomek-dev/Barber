package com.app.barber.other.dto;

import com.app.barber.model.Worker;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ServiceOutputDto {

    private Long id;
    private Set<String> workers;
    private String name;
    private String description;
    private Double price;
    private Integer time;

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers.stream()
                .map(Worker::getName)
                .collect(Collectors.toSet());
    }
}
