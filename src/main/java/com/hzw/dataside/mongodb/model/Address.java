package com.hzw.dataside.mongodb.model;

/**
 * @author hzw
 * @date 2019/7/18  6:16 PM
 * @Description:
 */
public class Address {
  private String street;
  private String city;
  private String zip;

  public Address() {
  }

  public Address(String street, String city, String zip) {
    this.street = street;
    this.city = city;
    this.zip = zip;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(final String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(final String zip) {
    this.zip = zip;
  }

  @Override
  public String toString() {
    return "Address{" +
        "street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", zip='" + zip + '\'' +
        '}';
  }

  // Rest of implementation
}