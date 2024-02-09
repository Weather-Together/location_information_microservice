package com.locationinformationmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import com.locationinformationmicroservice.facade.LocationInformationFacade;

@RestController
@RequestMapping("/api/v0")
public class LocationInformationController {
  
  
  @Autowired
  @GetMapping("/information")
  @ResponseBody
  public HashMap<String,String> location_information() {    
    String lat = "35.75";
    String lon = "-104.95";
    return LocationInformationFacade.getDetails(lat, lon);
}

}
