package net.dg.restaurantservice.repository;

import net.dg.restaurantservice.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

  @Query("SELECT r FROM Restaurant r WHERE r.id = :id")
  Optional<Restaurant> findRestaurantById(Long id);

  @Query("SELECT r from Restaurant r WHERE r.name like %:name%")
  Optional<Restaurant> findRestaurantByName(@Param("name") String name);
}
