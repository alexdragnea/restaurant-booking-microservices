package net.dg.restaurantservice.service;

import net.dg.restaurantservice.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

  Restaurant save(Restaurant restaurant);

  Restaurant getRestaurantById(Long id);

  void deleteRestaurantById(Long id);

  List<Restaurant> getAllRestaurants();

  Restaurant updateRestaurant(Restaurant restaurant);

  Restaurant getRestaurantByName(String name);
}
