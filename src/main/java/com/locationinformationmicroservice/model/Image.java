package com.locationinformationmicroservice.model;
import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "image")
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;
  private String country;
  private String state;
  private List<String> picUrls;
  public Long getId() {
      return id;
  }
  public void setId(Long id) {
      this.id = id;
  }
  public String getCountry() {
      return country;
  }
  public void setCountry(String country) {
      this.country = country;
  }
  public String getState() {
      return state;
  }
  public void setState(String state) {
      this.state = state;
  }
  public List<String> getPics() {
      return picUrls;
  }
  public void setPics(List<String> picUrls) {
      this.picUrls = picUrls;
  }
}
