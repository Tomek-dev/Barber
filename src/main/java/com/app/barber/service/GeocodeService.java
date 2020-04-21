package com.app.barber.service;

import com.app.barber.other.DataParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *  Gets data from api and return information about longitude and latitude
 */

@Service
public class GeocodeService {

    private static final String URL = ""; //https://nominatim.openstreetmap.org/search?q=
    private static final String PARAM = "&format=geocodejson&limit=1";

    public Double[] geocoder(String address, String city, String local){
        final RestTemplate template = new RestTemplate();
        String json = template.getForObject(URL + address + "+" + city + "+" + local + PARAM, String.class);
        return DataParser.getCoordinate(json);
    }
}
