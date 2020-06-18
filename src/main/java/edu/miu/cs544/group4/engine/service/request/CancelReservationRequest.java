package edu.miu.cs544.group4.engine.service.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class CancelReservationRequest {
    private String email;
    @NotBlank(message = "Reservation code is mandatory")
    private String reservationCode;
}
