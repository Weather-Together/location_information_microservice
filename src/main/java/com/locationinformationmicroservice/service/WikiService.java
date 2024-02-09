package com.locationinformationmicroservice.service;

import java.util.HashMap;

public class WikiService {
  public static String getLink (HashMap<String, String> location) {
    return location.get("state") + "url";    
  }
}
