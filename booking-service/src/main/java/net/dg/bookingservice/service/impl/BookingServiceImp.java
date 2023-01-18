package net.dg.bookingservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import net.dg.bookingservice.entity.Booking;
import net.dg.bookingservice.entity.dto.RestaurantDto;
import net.dg.bookingservice.exception.BookingNotFoundException;
import net.dg.bookingservice.exception.RestaurantNotFoundException;
import net.dg.bookingservice.feign.RestaurantClient;
import net.dg.bookingservice.kafka.producer.BookingProducer;
import net.dg.bookingservice.repository.BookingRepository;
import net.dg.bookingservice.service.BookingService;
import net.dg.bookingservice.util.UtilityClass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingService {

  private final BookingRepository bookingRepository;
  private final RestaurantClient restaurantClient;
  private final BookingProducer bookingProducer;

  @Override
  public RestaurantDto getRestaurantByName(String name) {
    return restaurantClient.getRestaurantByName(name);
  }

  @Override
  public Booking getBookingById(Long id) {

    Optional<Booking> existingBook = bookingRepository.findBookingById(id);
    if (existingBook.isPresent()) {
      return existingBook.get();
    }

    throw new BookingNotFoundException("Booking with given id not found");
  }

  @Override
  public void deleteBookingById(Long id) {
    Optional<Booking> existingBooking = bookingRepository.findBookingById(id);

    if (existingBooking.isPresent()) {

      bookingRepository.deleteById(id);
    }

    throw new BookingNotFoundException("Booking with given id not found");
  }

  @Override
  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }

  @Override
  public Booking updateBooking(Booking booking) {
    UtilityClass.validateDateAndTime(booking);
    return bookingRepository.save(booking);
  }

  @Override
  public void saveBooking(Booking booking) throws JsonProcessingException {

    RestaurantDto restaurantDto = restaurantClient.getRestaurantByName(booking.getRestaurantName());

    UtilityClass.validateDateAndTime(booking);
    if (restaurantDto.getName().equals(booking.getRestaurantName())) {
      bookingRepository.save(booking);
      Optional<Booking> savedBooking =
          bookingRepository.findBookingByFullNameAndRestaurantNameAndCreatedAt(
              booking.getFullName(),
              booking.getRestaurantName(),
              booking.getEmail(),
              booking.getCreatedAt());
      bookingProducer.sendMessage(savedBooking.get());
    } else {

      throw new RestaurantNotFoundException("Restaurant with given name doesn't exists.");
    }
  }
}
