package edu.miu.cs544.group4.engine.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author knguyen93
 */
public class DateUtils {
  public static Date generatePastDate(int daysBefore) {
    return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).minusDays(daysBefore).toInstant());
  }

  public static Date generateFutureDate(int daysBefore) {
    return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).plusDays(daysBefore).toInstant());
  }

}
