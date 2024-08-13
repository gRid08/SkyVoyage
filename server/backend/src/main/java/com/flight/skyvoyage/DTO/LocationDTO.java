package com.flight.skyvoyage.DTO;

public class LocationDTO {
  private String name;
  private String iataCode;

  public LocationDTO(String name, String iataCode) {
    this.name = name;
    this.iataCode = iataCode;
  }

  public String getName() {
    return name;
  }

  public String getIataCode() {
    return iataCode;
  }


}
