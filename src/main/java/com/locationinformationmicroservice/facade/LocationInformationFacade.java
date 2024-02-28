package com.locationinformationmicroservice.facade;

import java.util.HashMap;

import com.locationinformationmicroservice.service.GeocodingService;
import com.locationinformationmicroservice.service.ImageService;
import com.locationinformationmicroservice.service.WikiService;


public class LocationInformationFacade {
  public static HashMap<String, Object> getDetails (String lat, String lon, String image_api_key, String geo_api_key) {
    HashMap<String, Object> details = new HashMap<String, Object>();
    HashMap<String, String> location = GeocodingService.getDetails(lat, lon, geo_api_key);
    
    // @Value("${mbk.secret}")
    // private String SECRET_KEY;
    // Input the values
    details.put("images", ImageService.getImages(location.get("state"), image_api_key));
    details.put("wiki", WikiService.getLink(location));
    details.put("lat", lat);
    details.put("lon", lon);
    details.put("city", location.get("city"));
    details.put("state", location.get("state"));
    details.put("country", location.get("country"));
    // details.put("secret", SECRET_KEY);
    return  details;
  }
}
