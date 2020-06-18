package edu.miu.cs544.group4.engine.util;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/**
 * @author knguyen93
 */
public class DateUtils {
  private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
  private static final DateFormat TIME_FORMAT = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.US);

  public static Date generatePastDate(int daysBefore) {
    return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).minusDays(daysBefore).toInstant());
  }

  public static Date generateFutureDate(int daysAfter) {
    return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).plusDays(daysAfter).toInstant());
  }

  public static Date now() {
    return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
  }

  // "08/06/2009"
  public static Date formatDate(String dateString) {
    Date date = new Date();
    try {
      date = DATE_FORMAT.parse(dateString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return date;
  }

  public static LocalDateTime formatLocalDateTime(String dateString) {
    try {
      return LocalDateTime.parse(dateString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // "3:05 pm"
  public static Date formatTime(String timeString) {
    Date time = new Date();
    try {
      time = TIME_FORMAT.parse(timeString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return time;
  }

  private static LocalDateTime fromDate(Date aDate) {
    return aDate.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }

  public static Date fromDateAfter(Date fromDate, int daysAfter) {
    return Date.from(fromDate(fromDate).atZone(ZoneId.systemDefault()).plusDays(daysAfter).toInstant());
  }

  public static void main(String[] args) {
    // create an LocalDateTime object
    LocalDateTime lt
        = LocalDateTime
        .parse("2018-12-30T19:34:50.63");

    // print result
    System.out.println("LocalDateTime : "
        + lt);
  }
}
