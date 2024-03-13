package com.locationinformationmicroservice.facade;

import com.locationinformationmicroservice.ImageRepository;
import com.locationinformationmicroservice.model.Image;
import java.util.Optional;

public class TestFacade {
  
  
  public static Optional<Image> findImage (String city, String state, ImageRepository imageRepository) {
    return imageRepository.findFirstByCityAndStateAndRegionAndCountry(city, state, null, null);
  }
}
