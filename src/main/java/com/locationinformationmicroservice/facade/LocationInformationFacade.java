package com.locationinformationmicroservice.facade;

import java.util.HashMap;

import com.locationinformationmicroservice.service.GeocodingService;
import com.locationinformationmicroservice.service.ImageService;
import com.locationinformationmicroservice.service.WikiService;

import com.locationinformationmicroservice.model.Image;
import com.locationinformationmicroservice.ImageRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class LocationInformationFacade {
  public static HashMap<String, Object> getDetails (String lat, String lon, String image_api_key, String geo_api_key, ImageRepository imageRepository) {
    HashMap<String, Object> details = new HashMap<String, Object>();
    HashMap<String, String> location = GeocodingService.getDetails(lat, lon, geo_api_key);
    HashMap<String, String> wiki = WikiService.getLink(location);
    List<String> pics = new ArrayList<>();
    Optional<Image> image = LocationInformationFacade.dbImage(location, wiki.get("specificity"), imageRepository);

    if (image.isEmpty()) {
      pics = ImageService.getImages(location.get(wiki.get("specificity")), image_api_key);
    }


    details.put("images", pics);
    details.put("wiki", wiki.get("url"));
    details.put("lat", lat);
    details.put("lon", lon);
    details.put("city", location.get("city"));
    details.put("state", location.get("state"));
    details.put("region", location.get("region"));
    details.put("country", location.get("country"));
    return  details;
  }

  private static Optional<Image> dbImage(HashMap<String, String> location, String specificity, ImageRepository imageRepository) {
    String city = location.get("city");
    String state = location.get("state");
    String region = location.get("region");
    String country = location.get("country");
    return imageRepository.findFirstByCityAndStateAndRegionAndCountry(city, state, region, country);
  }
}
