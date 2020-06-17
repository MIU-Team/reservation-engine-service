package edu.miu.cs544.group4.engine.service.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author knguyen93
 */
@Data
@ToString
public class FlightRequest {
  @NotBlank(message = "Destination Airport code is mandatory")
  private String destinationCode;
  @NotBlank(message = "Departure Airport code is mandatory")
  private String originCode;
  @NotBlank(message = "Flight date is mandatory")
  private String date;
}
