package net.dg.bookingservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import net.dg.bookingservice.entity.Booking;
import net.dg.bookingservice.entity.dto.RestaurantDto;
import net.dg.bookingservice.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {

  private final BookingService bookingService;

  @GetMapping
  public ResponseEntity<List<Booking>> getAllBookings() {
    return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
    return new ResponseEntity<>(bookingService.getBookingById(id), HttpStatus.OK);
  }

  @GetMapping("/test")
  public ResponseEntity<RestaurantDto> getRestaurantByName(@RequestParam String name) {

    return new ResponseEntity<>(bookingService.getRestaurantByName(name), HttpStatus.CREATED);
  }

  @PostMapping
  public ResponseEntity<Booking> createNewRestaurant(@RequestBody Booking booking)
      throws JsonProcessingException {

    bookingService.saveBooking(booking);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBookingById(@PathVariable Long id) {

    bookingService.deleteBookingById(id);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Booking> updateRestaurantById(
      @PathVariable Long id, @RequestBody Booking booking) {

    Booking existingBooking = bookingService.getBookingById(id);

    existingBooking.setFullName(booking.getFullName());
    existingBooking.setEmail(booking.getEmail());
    existingBooking.setRestaurantName(booking.getRestaurantName());
    existingBooking.setStatus(booking.getStatus());
    existingBooking.setDate(booking.getDate());
    existingBooking.setTime(booking.getTime());

    return new ResponseEntity<>(bookingService.updateBooking(existingBooking), HttpStatus.OK);
  }
}
