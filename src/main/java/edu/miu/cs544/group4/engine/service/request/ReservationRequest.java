package edu.miu.cs544.group4.engine.service.request;

import edu.miu.cs544.group4.engine.model.Customer;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author knguyen93
 */
@Data
@ToString
public class ReservationRequest {
  @NotBlank(message = "Name is mandatory")
  private String name;
  private String email;
  private String phoneNumber;
  private CustomerRequest customerRequest;
  @NotBlank(message = "Reservation should have atleast one FlightNumber")
  private List<String> flightNumbers;
}
