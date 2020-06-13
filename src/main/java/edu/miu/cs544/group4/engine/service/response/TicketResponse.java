package edu.miu.cs544.group4.engine.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
    private Integer id;
    private String ticketNumber;
    private LocalDate flightDate;
    private ReservationResponse reservation;
    private PassengerResponse passenger;
}
