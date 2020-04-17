package com.app.barber.model;

import com.app.barber.other.enums.Star;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    private LocalDateTime date;

    @ManyToOne
    private OAuthUser owner;

    @ManyToOne
    private Worker worker;

    @ManyToOne
    private Service service;

    @ManyToOne
    private Barber barber;

    private Star star;

    @OneToMany(mappedBy = "review")
    private Set<Report> reports = new HashSet<>();

    public Review() {
        date = LocalDateTime.now();
    }
}
