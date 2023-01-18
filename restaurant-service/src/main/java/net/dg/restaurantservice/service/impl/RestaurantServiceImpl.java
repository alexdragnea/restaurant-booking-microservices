package net.dg.restaurantservice.service.impl;

import lombok.AllArgsConstructor;
import net.dg.restaurantservice.entity.Restaurant;
import net.dg.restaurantservice.exception.RestaurantNotFoundException;
import net.dg.restaurantservice.repository.RestaurantRepository;
import net.dg.restaurantservice.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

  private RestaurantRepository restaurantRepository;

  @Override
  public Restaurant save(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);
  }

  @Override
  public Restaurant getRestaurantById(Long id) {
    return restaurantRepository
        .findRestaurantById(id)
        .orElseThrow(
            () -> new RestaurantNotFoundException("Restaurant with given id cannot be found."));
  }

  @Override
  public void deleteRestaurantById(Long id) {

    Optional<Restaurant> existingRestaurant = restaurantRepository.findById(id);

    if (existingRestaurant.isPresent()) {
      restaurantRepository.deleteById(id);
    }

    throw new RestaurantNotFoundException("Restaurant with given id cannot be found.");
  }

  @Override
  public List<Restaurant> getAllRestaurants() {
    return restaurantRepository.findAll();
  }

  @Override
  public Restaurant updateRestaurant(Restaurant restaurant) {

    return restaurantRepository.save(restaurant);
  }

  @Override
  public Restaurant getRestaurantByName(String name) {
    return restaurantRepository
        .findRestaurantByName(name)
        .orElseThrow(
            () -> new RestaurantNotFoundException("Restaurant with given name cannot be found."));
  }
}
