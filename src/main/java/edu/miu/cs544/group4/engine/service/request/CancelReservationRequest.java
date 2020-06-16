package edu.miu.cs544.group4.engine.service.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CancelReservationRequest {
    private String email;
    private String reservationCode;
}
