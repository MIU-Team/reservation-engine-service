package edu.miu.cs544.group4.engine.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
    private Integer id;
    private String ticketNumber;
    private Date flightDate;
    private ReservationResponse reservation;
    private PassengerResponse passenger;
    private FlightResponse flight;
}
