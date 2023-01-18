package net.dg.bookingservice.enums;

public enum Status {
  WAITING_FOR_APPROVAL("Waiting for approval"),
  BOOKED("Booked");

  String value;

  Status(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
