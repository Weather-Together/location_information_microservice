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
      pics = LocationInformationFacade.imageLookupAndSave(location, wiki.get("specificity"), image_api_key, imageRepository);
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
    if (specificity == "state") {
      city = "none";
    }
    else if (specificity == "region") {
      city = "none";
      state = "none";
    }
    else if (specificity == "country") {
      city = "none";
      state = "none";
      region = "none";
    }
    return imageRepository.findFirstByCityAndStateAndRegionAndCountry(city, state, region, country);
  }

  private static List<String> imageLookupAndSave (HashMap<String, String> location, String specificity, String image_api_key, ImageRepository imageRepository) {
    // set location to proper specificity to save to database
    String city = location.get("city");
    String state = location.get("state");
    String region = location.get("region");
    String country = location.get("country");
    if (specificity == "state") {
      city = "none";
    }
    else if (specificity == "region") {
      city = "none";
      state = "none";
    }
    else if (specificity == "country") {
      city = "none";
      state = "none";
      region = "none";
    }

    // look up images for location
    List<String> pics = ImageService.getImages(location.get(specificity), image_api_key);

    //if no pics returned, go to next level of specificity

    pics = LocationInformationFacade.checkImageSpecificity(pics, specificity, location, image_api_key);
    
    // save images to database
    Image image = new Image();
    image.setCountry(country);
    image.setState(state);
    image.setRegion(region);
    image.setCity(city);
    image.setPics(pics);
    imageRepository.save(image);


    return pics;
  }

  private static List<String> checkImageSpecificity (List<String> pics, String specificity, HashMap<String, String> location, String image_api_key) {
    if (pics.isEmpty()) {
      if (specificity == "city") {
        specificity = "state";
      }
      else if (specificity == "state") {
        specificity = "region";
      }
      else if (specificity == "region") {
        specificity = "country";
      }
      try {Thread.sleep(1000);} catch (InterruptedException e) {}
      pics = ImageService.getImages(location.get(specificity), image_api_key);
      if (pics.isEmpty() && specificity == "country") { pics.add("https://fastly.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U"); }
      pics = LocationInformationFacade.checkImageSpecificity(pics, specificity, location, image_api_key);
      return pics;
    }
    else {
      return pics;
    }
  }

}
