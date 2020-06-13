package edu.miu.cs544.group4.engine.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer capacity;
    private String flightNumber;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private AirlineResponse airline;
    private AirportResponse origin;
    private AirportResponse destination;

}
