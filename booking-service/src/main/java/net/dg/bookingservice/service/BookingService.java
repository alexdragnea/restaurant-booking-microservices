package net.dg.bookingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.dg.bookingservice.entity.Booking;
import net.dg.bookingservice.entity.dto.RestaurantDto;

import java.util.List;

public interface BookingService {

  void saveBooking(Booking booking) throws JsonProcessingException;

  RestaurantDto getRestaurantByName(String name);

  Booking getBookingById(Long id);

  void deleteBookingById(Long id);

  List<Booking> getAllBookings();

  Booking updateBooking(Booking booking);
}
