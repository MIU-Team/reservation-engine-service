package edu.miu.cs544.group4.engine.service.request;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SaveFlightRequest {
	private Integer id;

	@Positive(message = "Flight capacity should be more than zero")
	private Integer capacity;
	@NotBlank(message = "Number of available seats is mandatory")
	private Integer availableSeats;
	@NotBlank(message = "Flight number is mandatory")
	@Size(max = 4, message = "Flight number should be 4 charachters")
	private String flightNumber;
	private Date departureTime;
	private Date arrivalTime;
	@Positive(message = "Airline Id should be more than zero")
	private Integer airlineId;
	@Positive(message = "Depurtuare airport Id should be more than zero")
	private Integer originAirportId;
	@Positive(message = "Destination airport Id should be more than zero")
	private Integer destinationAirportId;
}
