package com.locationinformationmicroservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locationinformationmicroservice.model.Image;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
  Optional<Image> findFirstByCityAndStateAndRegionAndCountry(String city, String state, String region, String country);
}