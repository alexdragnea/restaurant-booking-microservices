package net.dg.bookingservice.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dg.bookingservice.entity.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookingProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(BookingProducer.class);
  private final ObjectMapper objectMapper;
  private final KafkaTemplate<String, String> kafkaTemplate;

  @Value("${topic.name}")
  private String bookingTopic;

  public BookingProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }

  public String sendMessage(Booking booking) throws JsonProcessingException {
    String orderAsMessage = objectMapper.writeValueAsString(booking);
    kafkaTemplate.send(bookingTopic, orderAsMessage);

    LOGGER.info("Booking produced {}", orderAsMessage);

    return "message sent";
  }
}
