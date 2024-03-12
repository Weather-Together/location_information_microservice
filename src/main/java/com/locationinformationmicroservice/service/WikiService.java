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

      if (articleNumber > 0 && city != null) {
        url = WikiService.fullURL(articleNumber, result);
      } else {
        String state = location.get("state");
        String stateUri = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=info&inprop=url&titles=" + state;
        RestTemplate stateRestTemplate = new RestTemplate();
        String stateResult = stateRestTemplate.getForObject(stateUri, String.class);
        Integer stateArticleNumber = WikiService.returnsArticle(stateResult);
        url = "this";
        if (stateArticleNumber > 0 && state != null) {
          url = WikiService.fullURL(stateArticleNumber, stateResult);
        } 
      //   else {
      //     String region = location.get("region");
      //     String regionUri = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=info&inprop=url&titles=" + region;
      //     RestTemplate regionRestTemplate = new RestTemplate();
      //     String regionResult = regionRestTemplate.getForObject(regionUri, String.class);
      //     Integer regionArticleNumber = WikiService.returnsArticle(regionResult);
  
      //     if (regionArticleNumber > 0 && region != null) {
      //       url = WikiService.fullURL(regionArticleNumber, regionResult);
      //     } 
      //     else {
      //         String country = location.get("country");
      //         String countryUri = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=info&inprop=url&titles=" + country;
      //         RestTemplate countryRestTemplate = new RestTemplate();
      //         String countryResult = countryRestTemplate.getForObject(countryUri, String.class);
      //         Integer countryArticleNumber = WikiService.returnsArticle(countryResult);
      
      //         if (countryArticleNumber > 0) {
      //           url = WikiService.fullURL(countryArticleNumber, countryResult);
      //         }
      //       }
      //     }
        }
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
