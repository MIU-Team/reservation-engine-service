package edu.miu.cs544.group4.engine.service.request;

import edu.miu.cs544.group4.engine.model.Passenger;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ConfirmReservationRequest {
    private String email;
    private String reservationCode;
    private List<Passenger> passengers;
}
