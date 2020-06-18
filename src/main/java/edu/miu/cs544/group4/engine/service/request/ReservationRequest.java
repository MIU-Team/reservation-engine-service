package edu.miu.cs544.group4.engine.service.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author knguyen93
 */
@Data
@ToString
public class ReservationRequest {
  @NotBlank(message = "Name is mandatory")
  private String name;
  @NotBlank(message = "Email is mandatory")
  private String email;
  @NotEmpty(message = "Reservation should contained at least one FlightNumber")
  private List<String> flightNumbers;
}
