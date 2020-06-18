package edu.miu.cs544.group4.engine.service.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author knguyen93
 */
@Data
@ToString
public class AgentReservationRequest extends ReservationRequest {
  private String customerEmail;
}
