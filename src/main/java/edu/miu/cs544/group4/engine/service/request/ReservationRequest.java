package edu.miu.cs544.group4.engine.service.request;

import edu.miu.cs544.group4.engine.model.Customer;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author knguyen93
 */
@Data
@ToString
public class ReservationRequest {
  private String name;
  private String email;
  private String phoneNumber;
  private List<String> flightNumbers;
}
