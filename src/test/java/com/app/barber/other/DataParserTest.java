package com.app.barber.other;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataParserTest {

    private String json;

    @Before
    public void init(){
        json = "{ \"type\": \"FeatureCollection\", \"geocoding\": { \"version\": \"0.1.0\", \"attribution\": \"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\", \"licence\": \"ODbL\", \"query\": \"Αγία Τριάδα, Αδωνιδος, Athens, Greece\" }, \"features\": [ { \"type\": \"Feature\", \"properties\": { \"geocoding\": { \"type\": \"place_of_worship\", \"label\": \"Αγία Τριάδα, Αδωνιδος, Άγιος Νικόλαος, 5º Δημοτικό Διαμέρισμα Αθηνών, Athens, Municipality of Athens, Regional Unit of Central Athens, Region of Attica, Attica, 11472, Greece\", \"name\": \"Αγία Τριάδα\", \"admin\": null } }, \"geometry\": { \"type\": \"Point\", \"coordinates\": [ 23.72949633941, 38.0051697 ] } } ] }";
    }

    @Test
    public void shouldReturnCoordinates(){
        assertEquals(23.72949633941, DataParser.getCoordinate(json)[1]);
        assertEquals(38.0051697, DataParser.getCoordinate(json)[0]);
    }

}