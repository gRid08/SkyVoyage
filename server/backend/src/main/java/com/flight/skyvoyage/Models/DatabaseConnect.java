package com.flight.skyvoyage.Models;

import com.amadeus.resources.FlightOrder.Traveler;
import com.amadeus.resources.FlightOrder.Name;
import com.amadeus.resources.FlightOrder.Contact;
import com.amadeus.resources.FlightOrder.Phone;
import com.amadeus.resources.FlightOrder.Document;
import com.google.gson.JsonObject;

public class DatabaseConnect {
  public static Traveler createTraveler(JsonObject travelerInfo) {
    String fname = travelerInfo.get("fname").getAsString();
    String lname = travelerInfo.get("lname").getAsString();
    String dateOfBirth = travelerInfo.get("dob").getAsString();

    // Create Name object
    Name name = new Name(fname, lname);

    // Create Phone object
    Phone phone = new Phone();
    phone.setCountryCallingCode("1");
    phone.setNumber("1231231234");
    phone.setDeviceType(Phone.DeviceType.MOBILE);

    // Create Contact object
    Contact contact = new Contact();
    contact.setPhones(new Phone[]{phone});

    // Create Document object
    Document document = new Document();
    document.setDocumentType(Document.DocumentType.PASSPORT);
    document.setNumber("00000000");
    document.setExpiryDate("2025-04-14");
    document.setNationality("ES");
    document.setHolder(true);
    document.setIssuanceCountry("ES");

    // Create Traveler object
    Traveler traveler = new Traveler();
    traveler.setId("1");
    traveler.setName(name);
    traveler.setDateOfBirth(dateOfBirth);
    traveler.setContact(contact);
    traveler.setDocuments(new Document[]{document});

    return traveler;
  }
}
