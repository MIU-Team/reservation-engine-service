package edu.miu.cs544.group4.engine.service.request;

import edu.miu.cs544.group4.engine.model.Passenger;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString
public class ConfirmReservationRequest {
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Reservation code is mandatory")
    private String reservationCode;
    @NotEmpty(message = "Reservation requires at least one passenger")
    private List<Passenger> passengers;
}
