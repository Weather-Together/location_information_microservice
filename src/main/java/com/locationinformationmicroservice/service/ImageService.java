package com.locationinformationmicroservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ImageService {
  public static List<String> getImages (String state, String image_api_key) {
    String uri = "https://api.unsplash.com/" + "search/photos/?client_id=" + image_api_key + "&query=" + state;
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);
    
    List<String> images = new ArrayList<>();
    Gson gson = new Gson();
    JsonObject jsonResult = gson.fromJson(result, JsonObject.class);
    JsonArray results = jsonResult.getAsJsonArray("results");

    for (JsonElement element : results) {
      JsonObject photo = element.getAsJsonObject();
      JsonObject urls = photo.getAsJsonObject("urls");
      String rawUrl = urls.get("full").getAsString();
      images.add(rawUrl);
    }

    return images;
  }
}
