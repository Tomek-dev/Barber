package com.app.barber.model;

import com.app.barber.other.enums.SocialType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private SocialType socialType;

    @ManyToOne
    private Barber barber;

}
