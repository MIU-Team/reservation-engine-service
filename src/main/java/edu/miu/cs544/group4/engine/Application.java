package edu.miu.cs544.group4.engine;

import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.service.AirlineService;
import edu.miu.cs544.group4.engine.service.AirportService;
import edu.miu.cs544.group4.engine.service.FlightService;
import edu.miu.cs544.group4.engine.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	@Autowired
	ReservationService reservationService;
	@Autowired
	FlightService flightService;
	@Autowired
	AirlineService airlineService;
	@Autowired
	AirportService airportService;


	@Bean
	void test() {
		/**
		 * create list of
		 * Airlines
		 * Flights
		 * Airports
		 *
		 */

		reservationService.makeReservation(customer, passengers, flights);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);





	}

}
