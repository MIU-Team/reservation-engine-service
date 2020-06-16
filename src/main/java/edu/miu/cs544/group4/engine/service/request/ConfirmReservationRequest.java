package edu.miu.cs544.group4.engine.service.request;

import edu.miu.cs544.group4.engine.model.Passenger;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ToString
public class ConfirmReservationRequest {
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Reservation code is mandatory")
    private String reservationCode;
    @NotBlank(message = "Reservation requires atleast one passenger")
    private List<Passenger> passengers;
}
