import React, { useState, useEffect } from 'react';

function BookingList() {
  const [flightData, setFlightData] = useState([]);

  useEffect(() => {
    
    const fetchData = async () => {
      try {
        const response = await fetch('http://localhost:8000/api/booking'); 
        if (response.ok) {
          const data = await response.json();
          setFlightData(data);
        } else {
          console.error('Error fetching data');
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    
    fetchData();
  }, []);

  return (
    <div>
      <h2>User Bookings</h2>
      <h2>Flight Details</h2>
      <ul>
        {flightData.map((flight) => (
          <li key={flight._id}>
            {/* <strong>Flight:</strong> {flight.flightName} */}
            <br />
            <strong>Price:</strong> {flight.flight.price?.currency} {flight.flight.price?.total}
            <br />
            <strong>Departure Airport:</strong> {flight.flight.itineraries[0].segments[0].departure.iataCode}
            <br />
            <strong>Arrival Airport:</strong> {flight.flight.itineraries[0].segments[0].arrival.iataCode}
            <br />
            <strong>Time</strong> {flight.departureTime}
            
          </li>
        ))}
      </ul>
    </div>
  );
}

export default BookingList;
