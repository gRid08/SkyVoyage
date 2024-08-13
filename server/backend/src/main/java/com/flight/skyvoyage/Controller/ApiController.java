package com.flight.skyvoyage.Controller;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Location;
import com.flight.skyvoyage.Amadeus.AmadeusConnect;
import com.flight.skyvoyage.DTO.LocationDTO;
import com.flight.skyvoyage.Models.DatabaseConnect;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:5173")
public class ApiController {

  @GetMapping("/hello")
  public String getHello(){
    return "Hello, World!";
  }

  @GetMapping("/locations")
  public List<LocationDTO> locations(@RequestParam(required = true) String keyword) throws ResponseException {
    System.out.println("Received request for locations with keyword: " + keyword);
    try {
      // Call the modified location method
      Location[] locations = AmadeusConnect.INSTANCE.location(keyword);
      System.out.println("Locations found: " + Arrays.toString(locations));

      // Convert Location[] to List<LocationDTO>
      List<LocationDTO> locationDTOs = Arrays.stream(locations)
              .map(location -> new LocationDTO(location.getName(), location.getIataCode()))
              .collect(Collectors.toList());

      return locationDTOs;
    } catch (ResponseException e) {
      System.err.println("Error fetching locations: " + e.getMessage());
      throw e; // Or return an appropriate response
    }
  }


  @GetMapping("/flights")
  public FlightOfferSearch[] flights(@RequestParam(required = true) String origin,
                                     @RequestParam(required = true) String destination,
                                     @RequestParam(required = true) String departDate,
                                     @RequestParam(required = true) String adults,
                                     @RequestParam(required = true) String children,
                                     @RequestParam(required = false) String travelClass,
                                     @RequestParam(required = false) String returnDate) throws Exception {
    return AmadeusConnect.INSTANCE.flights(origin, destination, departDate, adults,children,travelClass, returnDate);
  }

  @PostMapping("/confirm")
  public FlightPrice confirm(@RequestBody(required = true) FlightOfferSearch search)throws ResponseException{
    return AmadeusConnect.INSTANCE.confirm(search);
  }

  @PostMapping("/traveler")
  public FlightOrder.Traveler traveler(@RequestBody(required = true)JsonObject travelerInfo){
    return DatabaseConnect.createTraveler(travelerInfo.get("data").getAsJsonObject());
  }

  @PostMapping("/order")
  public FlightOrder order(@RequestBody(required = true)JsonObject order)throws ResponseException{
    return AmadeusConnect.INSTANCE.order(order);
  }

}
