package com.locationinformationmicroservice.service;

import org.springframework.web.client.RestTemplate;

public class ImageService {
  public static String getImages (String state) {
    String uri = "https://api.unsplash.com/" + "search/photos/?client_id=" + "tYtkSb6r-DJk9OdkgGFOURWL5fCgIdv_m_6khsBDugo" + "&query=" + state;
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);

    
    return result;
  }
}
