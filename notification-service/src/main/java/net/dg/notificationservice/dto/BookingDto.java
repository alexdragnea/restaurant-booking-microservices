package net.dg.notificationservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookingDto {

  private Long id;

  @JsonProperty("full_name")
  private String fullName;

  private String email;

  @JsonProperty("restaurant_name")
  private String restaurantName;

  private LocalDate date;
  private LocalTime time;

  private String status;
}
