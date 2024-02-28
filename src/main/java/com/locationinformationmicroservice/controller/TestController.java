package com.locationinformationmicroservice.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.locationinformationmicroservice.fixtures.ImageFixture;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class TestController {
  @GetMapping("/test")
  @ResponseBody
  public static List<String> getImages(@RequestParam String image_api_key, @RequestParam String query) {
    // String uri = "https://api.unsplash.com/search/photos/?client_id=" + image_api_key + "&query=" + state;
    // RestTemplate restTemplate = new RestTemplate();
    // String result = restTemplate.getForObject(uri, String.class);

    List<String> images = new ArrayList<>();
    String result = ImageFixture.getObject();
    Gson gson = new Gson();
    JsonObject jsonResult = gson.fromJson(result, JsonObject.class);
    JsonArray results = jsonResult.getAsJsonArray("results");

    for (JsonElement element : results) {
      JsonObject photo = element.getAsJsonObject();
      JsonObject urls = photo.getAsJsonObject("urls");
      String rawUrl = urls.get("raw").getAsString();
      images.add(rawUrl);
    }

    return images;
  }
}