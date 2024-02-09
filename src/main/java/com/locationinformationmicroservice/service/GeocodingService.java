package com.locationinformationmicroservice.service;

import java.util.HashMap;

public class GeocodingService {
  public static HashMap<String, String> getDetails (String lat, String lon) {
    HashMap<String, String> details = new HashMap<String, String>();

    // Input the values
    details.put("Locationlat", lat);
    details.put("Locationlon", lon);
    details.put("city", "city");
    details.put("state", "Utah");
    details.put("country", "country");
    return details;
  }
}
