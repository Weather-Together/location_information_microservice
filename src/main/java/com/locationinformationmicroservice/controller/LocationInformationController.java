package com.locationinformationmicroservice.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import com.locationinformationmicroservice.facade.LocationInformationFacade;

@RestController
@RequestMapping("/api/v0")
public class LocationInformationController {
  
  
  @GetMapping("/information")
  @ResponseBody
  public HashMap<String,String> location_information(@RequestParam String lat, @RequestParam String lon, @RequestParam String image_api_key, @RequestParam String geo_api_key) {    
    return LocationInformationFacade.getDetails(lat, lon, image_api_key, geo_api_key);
}

}
