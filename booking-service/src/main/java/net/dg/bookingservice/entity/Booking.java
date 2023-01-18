package net.dg.bookingservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dg.bookingservice.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("full_name")
  private String fullName;

  private String email;

  @JsonProperty("restaurant_name")
  private String restaurantName;

  private LocalDate date;
  private LocalTime time;

  private String status = String.valueOf(Status.WAITING_FOR_APPROVAL.getValue());

  private Instant createdAt = Instant.now();
}
