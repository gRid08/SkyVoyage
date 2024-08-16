import { ref, set, push, get, query, orderBy, limit } from 'firebase/database';
import { getUserData } from './storage'; 


export const storeBooking = (user, ticketDetails) => {
  const database = getDatabase(); 
  const bookingsRef = ref(database, 'bookings'); 

  
  const newBookingRef = push(bookingsRef);

  
  const bookingData = {
    user: user, 
    ticketDetails: ticketDetails, 
    timestamp: Date.now(), 
  };

  
  return set(newBookingRef, bookingData);
};


export const getUserBookings = async () => {
  const database = getDatabase();
  const bookingsRef = ref(database, 'bookings');

  
  const userId = getUserData().cardHolder;

  
  const userBookingsQuery = query(
    bookingsRef,
    orderByChild('cardHolder'), 
    equalTo(userId)
  );

  
  const snapshot = await get(userBookingsQuery);
  const userBookings = [];
  snapshot.forEach((childSnapshot) => {
    userBookings.push(childSnapshot.val());
  });

  return userBookings;
};

export {database};