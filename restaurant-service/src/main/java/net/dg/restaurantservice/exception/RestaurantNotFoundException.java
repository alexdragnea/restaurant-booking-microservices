package net.dg.restaurantservice.exception;

public class RestaurantNotFoundException extends RuntimeException {

  public RestaurantNotFoundException(String message) {
    super(message);
  }
}
