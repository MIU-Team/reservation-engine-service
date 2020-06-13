package edu.miu.cs544.group4.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "edu.miu.common, edu.miu.cs.cs544")
@SpringBootApplication
public class Application {
//	@Autowired
//	ReservationService reservationService;
//	@Autowired
//	FlightService flightService;
//	@Autowired
//	AirlineService airlineService;
//	@Autowired
//	AirportService airportService;


	void test() {
		/**
		 * create list of
		 * Airlines
		 * Flights
		 * Airports
		 *
		 */

		//reservationService.makeReservation(customer, passengers, flights);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
