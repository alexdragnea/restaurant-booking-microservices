package net.dg.notificationservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dg.notificationservice.dto.BookingDto;
import net.dg.notificationservice.enums.Status;
import net.dg.notificationservice.feign.BookingClient;
import net.dg.notificationservice.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(org.apache.kafka.clients.consumer.KafkaConsumer.class);

  private static final String orderTopic = "${topic.name}";

  private final ObjectMapper objectMapper;
  private final NotificationService notificationService;
  private final BookingClient bookingClient;

  public KafkaConsumer(
      ObjectMapper objectMapper,
      NotificationService notificationService,
      BookingClient bookingClient) {
    this.objectMapper = objectMapper;
    this.notificationService = notificationService;
    this.bookingClient = bookingClient;
  }

  @KafkaListener(topics = orderTopic)
  public void consumeMessage(String message) throws JsonProcessingException {
    LOGGER.info("Message consumed {}", message);

    BookingDto bookingDto = objectMapper.readValue(message, BookingDto.class);

    bookingDto.setStatus(String.valueOf(Status.BOOKED.getValue()));
    bookingClient.updateBooking(bookingDto.getId(), bookingDto);
    String emailContent =
        "Hello "
            + bookingDto.getFullName()
            + ", your booking at "
            + bookingDto.getRestaurantName()
            + " is in status "
            + bookingDto.getStatus();
    notificationService.sendNotification(emailContent, bookingDto.getEmail());
  }
}
