package net.dg.notificationservice.service.impl;

import lombok.AllArgsConstructor;
import net.dg.notificationservice.service.NotificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final JavaMailSender emailSender;

  @Override
  public void sendNotification(String message, String recipient) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("noreply@baeldung.com");
    mailMessage.setTo(recipient);
    mailMessage.setSubject("Booking notification");
    mailMessage.setText(message);
    emailSender.send(mailMessage);
  }
}
