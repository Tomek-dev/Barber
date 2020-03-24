package com.app.barber.other;

import com.app.barber.other.exception.GeocodeException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataParser {

    public static Double[] getCoordinate(String json){
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(json);
            String data = object.get("features").toString();
            JSONArray array = (JSONArray) parser.parse(data);
            data = array.toJSONString().substring(1, array.toJSONString().length()-1);
            object = (JSONObject) parser.parse(data);
            data = object.get("geometry").toString();
            object = (JSONObject) parser.parse(data);
            data = object.get("coordinates").toString();
            array = (JSONArray) parser.parse(data);
            double latitude = Double.parseDouble(array.get(1).toString());
            double longitude = Double.parseDouble(array.get(0).toString());
            return new Double[]{latitude, longitude};
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new GeocodeException();
    }
}
