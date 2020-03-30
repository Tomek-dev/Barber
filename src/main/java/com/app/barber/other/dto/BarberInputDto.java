package com.app.barber.other.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BarberInputDto {

    @NotBlank
    @Size(min = 4, max = 36)
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotBlank
    private String local;


    public BarberInputDto() {
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
}
