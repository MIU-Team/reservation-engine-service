package edu.miu.cs544.group4.engine.service.request;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SaveFlightRequest {
	private Integer id;
	private Integer capacity;
	private String flightNumber;
	private Date departureTime;
	private Date arrivalTime;
	private Integer airlineId;
	private Integer originAirportId;
	private Integer destinationAirportId;
}
