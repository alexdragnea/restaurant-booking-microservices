package net.dg.bookingservice.util;

import net.dg.bookingservice.entity.Booking;
import net.dg.bookingservice.exception.DateTimeException;

import java.time.LocalDate;
import java.time.LocalTime;

public class UtilityClass {

  public static void validateDateAndTime(Booking booking) {

    LocalDate systemDate = LocalDate.now();

    LocalTime startingHour = LocalTime.parse("09:00");
    LocalTime endingHour = LocalTime.parse("21:00");

    if (booking.getDate().isBefore(systemDate)
        || booking.getTime().isAfter(endingHour)
        || booking.getTime().isBefore(startingHour)) {
      throw new DateTimeException("Invalid date or time.");
    }
  }
}
