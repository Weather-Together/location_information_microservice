package com.locationinformationmicroservice.service;

import java.util.HashMap;
import org.springframework.web.client.RestTemplate;

public class WikiService {
  public static String getLink (HashMap<String, String> location) {
    String uri = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=info&inprop=url&titles=" +location.get("state");
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);
    String url = result.split("canonicalurl\":\"")[1].replace("\"}}}}", "");

    return  url;    
  }
}
