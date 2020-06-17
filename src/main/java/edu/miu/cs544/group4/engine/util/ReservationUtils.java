package edu.miu.cs544.group4.engine.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author knguyen93
 */
public class ReservationUtils {
  public static String generateTicketNumber() {
    return RandomStringUtils.random(20, false, true);
  }

  public static String generateReservationCode() {
    return RandomStringUtils.random(6, true, true);
  }

  public static void main(String[] args) {
    int i = 0;
    while (i <= 100) {
      System.out.println(generateTicketNumber());
      System.out.println(generateReservationCode());
      i++;
    }
  }
}
