package net.dg.bookingservice.repository;

import net.dg.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

  @Query("SELECT b FROM Booking b WHERE b.id = :id")
  Optional<Booking> findBookingById(Long id);

  @Query(
      "SELECT b FROM Booking b WHERE b.fullName = :fullName and b.restaurantName = :restaurantName and b.email = :email and b.createdAt = :createdAt")
  Optional<Booking> findBookingByFullNameAndRestaurantNameAndCreatedAt(
      String fullName, String restaurantName, String email, Instant createdAt);
}
