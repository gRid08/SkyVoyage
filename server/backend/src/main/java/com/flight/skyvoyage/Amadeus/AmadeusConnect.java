package com.flight.skyvoyage.Amadeus;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Location;
import com.google.gson.JsonObject;

public enum AmadeusConnect {
  INSTANCE;
  private Amadeus amadeus;
  private AmadeusConnect(){
    this.amadeus = Amadeus
            .builder("xUWTRqx1ZBGGrlqzVVFGfABf0AXZenAY","VrygFrTRkEP50oQ6")
            .build();
  }

  public Location[] location(String keyword) throws ResponseException {
    return amadeus.referenceData.locations.get(Params
            .with("keyword", keyword)
            .and("subType", Locations.AIRPORT)
            .and("countryCode", "IN")); // Set countryCode to "IN" for India
  }


  public FlightOfferSearch[] flights(String origin, String destination, String departDate, String adults, String returnDate,String children,String travelClass) throws ResponseException {
    Params params = Params.with("originLocationCode", origin)
            .and("destinationLocationCode", destination)
            .and("departureDate", departDate)
            .and("adults", adults)
            .and("children",children)
            .and("travelClass",travelClass)
            .and("currencyCode","INR");

    if (returnDate != null && !returnDate.isEmpty()) {
      params.and("returnDate", returnDate);
    }

    return amadeus.shopping.flightOffersSearch.get(params);
  }

  public FlightPrice confirm(FlightOfferSearch offer)throws ResponseException{
    return amadeus.shopping.flightOffersSearch.pricing.post(offer);
  }

  public FlightOrder order(JsonObject order)throws ResponseException{
    return amadeus.booking.flightOrders.post(order);
  }

}
