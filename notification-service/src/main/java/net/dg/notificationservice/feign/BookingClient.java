package net.dg.notificationservice.feign;

import net.dg.notificationservice.dto.BookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BookingClient", url = "${booking.url}", decode404 = true)
public interface BookingClient {

  @PutMapping("/{id}")
  BookingDto updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto);
}
