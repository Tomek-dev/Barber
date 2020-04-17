package com.app.barber.other.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BarberOutputDto {

    private Long id;

    private String name;

    private String city;

    private String address;

    private String local;

    private Double latitude;

    private Double longitude;

}
