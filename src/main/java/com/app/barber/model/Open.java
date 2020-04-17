package com.app.barber.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Open {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DayOfWeek day;
    private LocalTime open;
    private LocalTime close;

    @ManyToOne
    private Barber barber;

}
