package com.locationinformationmicroservice.facade;

import java.util.HashMap;

import com.locationinformationmicroservice.service.GeocodingService;
import com.locationinformationmicroservice.service.ImageService;
import com.locationinformationmicroservice.service.WikiService;


public class LocationInformationFacade {
  public static HashMap<String, String> getDetails (String lat, String lon) {
    HashMap<String, String> details = new HashMap<String, String>();
    
    // Input the values
    details.put("Locationlat", GeocodingService.getDetails(lat, lon).get("Locationlat"));
    details.put("Locationlon", GeocodingService.getDetails(lat, lon).get("Locationlon"));
    details.put("Image", ImageService.getImages(GeocodingService.getDetails(lat, lon)));
    details.put("Wiki", WikiService.getLink(GeocodingService.getDetails(lat, lon)));
    details.put("lat", lat);
    details.put("lon", lon);
    return  details;
  }
}
