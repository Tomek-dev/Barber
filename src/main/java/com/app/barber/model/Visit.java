package com.app.barber.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime beginning;

    private LocalDateTime finish;

    @ManyToOne
    private OAuthUser customer;

    @ManyToOne
    private Service service;

    @ManyToOne
    private Worker worker;

    @ManyToOne
    private Barber barber;
}
