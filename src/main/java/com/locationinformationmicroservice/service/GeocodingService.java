package com.locationinformationmicroservice.service;

import java.util.HashMap;

import org.springframework.web.client.RestTemplate;

public class GeocodingService {
  public static HashMap<String, String> getDetails (String lat, String lon) {
    String uri = "https://geocode.maps.co/reverse?lat=" + lat + "&lon=" + lon + "&api_key=65c31494d50c3989330257zlbe7e8b8";
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);

    HashMap<String, String> details = new HashMap<String, String>();

    // Input the values
    details.put("Locationlat", lat);
    details.put("Locationlon", lon);
    details.put("city", result.split(",\"city\":\"")[1].split("\",\"")[0]);
    details.put("state", result.split(",\"state\":\"")[1].split("\",\"")[0]);
    details.put("country", result.split(",\"country\":\"")[1].split("\",\"")[0]);
    return details;
  }
}
