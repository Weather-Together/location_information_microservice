package com.locationinformationmicroservice.service;

import org.springframework.web.client.RestTemplate;

public class ImageService {
  public static String getImages (String state, String image_api_key) {
    String uri = "https://api.unsplash.com/" + "search/photos/?client_id=" + image_api_key + "&query=" + state;
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);
    String image = result.split("\"raw\":\"")[1].split("\",\"full\":")[0];
    
    return image;
  }
}
