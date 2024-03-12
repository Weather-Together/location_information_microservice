package com.locationinformationmicroservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locationinformationmicroservice.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {}