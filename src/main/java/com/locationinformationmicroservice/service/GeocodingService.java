package com.locationinformationmicroservice.service;

import java.util.HashMap;

import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GeocodingService {
  
  public static HashMap<String, String> getDetails (String lat, String lon, String geo_api_key) {
    String uri = "https://geocode.maps.co/reverse?lat=" + lat + "&lon=" + lon + "&api_key=" + geo_api_key;
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);
    
    HashMap<String, String> details = new HashMap<String, String>();

    Gson gson = new Gson();
    JsonObject jsonResult = gson.fromJson(result, JsonObject.class);
    JsonObject address = jsonResult.getAsJsonObject("address");
    String city = null;
    if (address.has("city") && !address.get("city").isJsonNull()) {
        city = address.get("city").getAsString();
    }
    String state = address.get("state").getAsString();
    String country = address.get("country").getAsString();

    // Input the values
    details.put("Locationlat", lat);
    details.put("Locationlon", lon);
    details.put("city", city);
    details.put("state", state);
    details.put("country", country);
    return details;
  }
}
