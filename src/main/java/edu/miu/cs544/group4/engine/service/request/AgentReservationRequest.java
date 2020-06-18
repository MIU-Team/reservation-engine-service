package edu.miu.cs544.group4.engine.service.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author knguyen93
 */
@Data
@ToString
public class AgentReservationRequest extends ReservationRequest {
  @NotBlank(message = "Customer email is mandatory")
  private String customerEmail;
}
