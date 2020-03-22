package com.app.barber.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class CoordinateService {

    private static final String URL = "";

    //TODO find free api geocoder

    public Double[] geocoder(String... address){
        RestTemplate template = new RestTemplate();
        double latitude = 0.0;
        double longitude = 0.0;
        return new Double[]{latitude, longitude};
    }
}
