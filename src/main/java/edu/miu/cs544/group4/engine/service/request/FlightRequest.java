package edu.miu.cs544.group4.engine.service.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author knguyen93
 */
@Data
@ToString
public class FlightRequest {
  private String destinationCode;
  private String originCode;
  private String date;
}
