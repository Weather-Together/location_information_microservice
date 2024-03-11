package com.locationinformationmicroservice.service;

import java.util.HashMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WikiService {
  public static String getLink (HashMap<String, String> location) {
    String city = location.get("city");
    String url = "";
    if (city != null) {
      String uri = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=info&inprop=url&titles=" + city;
      RestTemplate restTemplate = new RestTemplate();
      String result = restTemplate.getForObject(uri, String.class);
      Integer articleNumber = WikiService.returnsArticle(result);
      // String url = result.split("canonicalurl\":\"")[1].replace("\"}}}}", "");

      if (articleNumber > 0) {
        url = WikiService.fullURL(articleNumber, result);
      } else {
        String stateUri = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=info&inprop=url&titles=" +location.get("state");
        RestTemplate stateRestTemplate = new RestTemplate();
        String stateResult = stateRestTemplate.getForObject(stateUri, String.class);
        Integer stateArticleNumber = WikiService.returnsArticle(stateResult);

        if (stateArticleNumber > 0) {
          url = WikiService.fullURL(articleNumber, result);
        }
      }
    } else {
      url = "lol";
    }

    return  url;    
  }

  // Helper method to determine if location returns wiki article
  private static Integer returnsArticle (String locationInfo) {
    Gson gson = new Gson();
    JsonObject jsonResult = gson.fromJson(locationInfo, JsonObject.class);
    JsonObject query = jsonResult.getAsJsonObject("query");
    JsonObject pages = query.getAsJsonObject("pages");
    String pagesstring = new Gson().toJson(pages);
    JsonObject jsonObject = JsonParser.parseString(pagesstring).getAsJsonObject();

    String key = "";

    for (java.util.Map.Entry<String, com.google.gson.JsonElement> entry : jsonObject.entrySet()) {
        key = entry.getKey();
          }
    int articleInt = Integer.parseInt(key);
    return articleInt;      
    }
  // Helper method to return url from API call

  private static String fullURL (Integer articleNo, String result) {
    String key = Integer.toString(articleNo);
    Gson gson = new Gson();
    JsonObject jsonResult = gson.fromJson(result, JsonObject.class);
    JsonObject query = jsonResult.getAsJsonObject("query");
    JsonObject pages = query.getAsJsonObject("pages");
    JsonObject article = pages.getAsJsonObject(key);
    return article.get("fullurl").getAsString();
  }
}
