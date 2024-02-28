package com.locationinformationmicroservice.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.locationinformationmicroservice.fixtures.ImageFixture;


import java.util.HashMap;

@RestController
@RequestMapping("/api/v0")
public class TestController {
  
  @GetMapping("/test")
  @ResponseBody
  public String location_information(@RequestParam String image_api_key, @RequestParam String query) { 
    // String uri = "https://api.unsplash.com/" + "search/photos/?client_id=" + image_api_key + "&query=" + query;
    // RestTemplate restTemplate = new RestTemplate();
    // String result = restTemplate.getForObject(uri, String.class);

    String result = ImageFixture.getObject();
    String image = result.split("\"raw\":\"")[1].split("\",\"full\":")[0];
    
    return image;   
  }
}




