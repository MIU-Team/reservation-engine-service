package edu.miu.cs544.group4.engine.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer capacity;
    private Integer availableSeats;
    private String flightNumber;
    private Date departureTime;
    private Date arrivalTime;
    private AirlineResponse airline;
    private AirportResponse origin;
    private AirportResponse destination;

}
