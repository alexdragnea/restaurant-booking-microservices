package net.dg.restaurantservice.rest;

import lombok.AllArgsConstructor;
import net.dg.restaurantservice.entity.Restaurant;
import net.dg.restaurantservice.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

  private final RestaurantService restaurantService;

  @PostMapping
  public ResponseEntity<Restaurant> createNewRestaurant(@RequestBody Restaurant restaurant) {
    return new ResponseEntity<>(restaurantService.save(restaurant), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Restaurant>> getAllRestaurants() {
    return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
    return new ResponseEntity<>(restaurantService.getRestaurantById(id), HttpStatus.FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRestaurantById(@PathVariable Long id) {
    restaurantService.deleteRestaurantById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Restaurant> updateRestaurantById(
      @PathVariable Long id, @RequestBody Restaurant restaurant) {

    Restaurant existingRestaurant = restaurantService.getRestaurantById(id);

    existingRestaurant.setName(restaurant.getName());
    existingRestaurant.setAddress(restaurant.getAddress());

    return new ResponseEntity<>(
        restaurantService.updateRestaurant(existingRestaurant), HttpStatus.OK);
  }

  @GetMapping("/search")
  public ResponseEntity<Restaurant> getRestaurantByName(@RequestParam String name) {

    return new ResponseEntity<>(restaurantService.getRestaurantByName(name), HttpStatus.OK);
  }
}
