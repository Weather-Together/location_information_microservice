package com.locationinformationmicroservice.service;

import java.util.HashMap;

public class ImageService {
  public static String getImages (HashMap<String, String> location) {
    return location.get("state") + "image";
  }
}
