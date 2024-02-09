package com.locationinformationmicroservice.facade;

import java.util.HashMap;

import com.locationinformationmicroservice.service.GeocodingService;
import com.locationinformationmicroservice.service.ImageService;
import com.locationinformationmicroservice.service.WikiService;


public class LocationInformationFacade {
  public static HashMap<String, String> getDetails (String lat, String lon) {
    HashMap<String, String> details = new HashMap<String, String>();
    HashMap<String, String> location = GeocodingService.getDetails(lat, lon);
    // Input the values
    details.put("images", ImageService.getImages(location.get("state")));
    details.put("wiki", WikiService.getLink(location));
    details.put("lat", lat);
    details.put("lon", lon);
    details.put("city", location.get("city"));
    details.put("state", location.get("state"));
    details.put("country", location.get("country"));
    return  details;
  }
}
