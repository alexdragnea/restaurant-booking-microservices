package net.dg.bookingservice.feign;

import net.dg.bookingservice.entity.dto.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "RestaurantClient", url = "${restaurant.url}", decode404 = true)
public interface RestaurantClient {

  @GetMapping("/search")
  RestaurantDto getRestaurantByName(@RequestParam String name);
}
