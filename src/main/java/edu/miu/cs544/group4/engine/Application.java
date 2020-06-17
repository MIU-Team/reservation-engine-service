package edu.miu.cs544.group4.engine;

import edu.miu.cs544.group4.engine.model.Address;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.model.Customer;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.model.History;
import edu.miu.cs544.group4.engine.model.Passenger;
import edu.miu.cs544.group4.engine.model.Reservation;
import edu.miu.cs544.group4.engine.model.Role;
import edu.miu.cs544.group4.engine.model.Ticket;
import edu.miu.cs544.group4.engine.repository.AirlineRepository;
import edu.miu.cs544.group4.engine.repository.AirportRepository;
import edu.miu.cs544.group4.engine.repository.CustomerRepository;
import edu.miu.cs544.group4.engine.repository.FlightRepository;
import edu.miu.cs544.group4.engine.repository.ReservationRepository;
import edu.miu.cs544.group4.engine.util.DateUtils;
import edu.miu.cs544.group4.engine.util.ReservationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication(
		scanBasePackages = "edu.miu.common, edu.miu.common.config, edu.miu.cs.cs544, edu.miu.cs544.group4.engine")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	AirlineRepository airlineRepository;
	@Autowired
	AirportRepository airportRepository;
	@Autowired
	CustomerRepository customerRepository;

	/**
	 * Un-comment the @Bean to create the dummy data.
	 * Also make sure un-comment "ddl-auto: create" in the application.yml
	 */
//	@Bean
	public void testRepository() {
		History history = new History();
		history.setDescription("There is nothing such history");
		Address address = new Address();
		address.setCity("Dallas");
		address.setState("Texas");
		address.setZip("2343");
		address.setStreet("6789 Well done");

		Airline airline = new Airline();
		airline.setAddress(address);
		airline.setCode("UA");
		airline.setName("My Airline");
		airline.setHistory(history);
		airlineRepository.save(airline);

		Address address2 = new Address();
		address2.setCity("Fairfield");
		address2.setState("Iowa");
		address2.setZip("2343");
		address2.setStreet("6789 Main St");

		Address address3 = new Address();
		address3.setCity("Somewhere");
		address3.setState("Some State");
		address3.setZip("2343");
		address3.setStreet("6789 Blablala");

		Airport airport = new Airport();
		airport.setAddress(address2);
		airport.setCode("JFK");
		airport.setName("John F. Kennedy International Airport");

		Airport arrivalAirport = new Airport();
		arrivalAirport.setAddress(address3);
		arrivalAirport.setCode("MHA");
		arrivalAirport.setName("Maharishi Airport");

		airportRepository.save(airport);
		airportRepository.save(arrivalAirport);

		Flight flight = new Flight();
		flight.setAirline(airline);
		flight.setFlightNumber("2324");
		flight.setCapacity(100);
		flight.setDepartureTime(DateUtils.generatePastDate(29));
		flight.setArrivalTime(DateUtils.generatePastDate(28));
		flight.setDestination(arrivalAirport);
		flight.setOrigin(airport);

		flightRepository.save(flight);
		Address address4 = new Address();
		address4.setCity("Somewhere 4");
		address4.setState("Some State");
		address4.setZip("2343");
		address4.setStreet("6789 Blablala");

		Passenger passenger = new Passenger();
		passenger.setAddress(address4);
		passenger.setFirstName("Mike");
		passenger.setLastName("John");

		Passenger passenger2 = new Passenger();
		passenger2.setAddress(address4);
		passenger2.setFirstName("Samuel");
		passenger2.setLastName("Bwambale");

		Customer customer = new Customer();
		customer.setEmail("khanh@gmail.com");
		customer.setPhoneNumber("83638483463846");
		customer.setRole(Role.CUSTOMER);
		customerRepository.save(customer);



		Ticket ticket = new Ticket();
		ticket.setPassenger(passenger);
		ticket.setTicketNumber(ReservationUtils.generateTicketNumber());
		ticket.setFlightDate(flight.getDepartureTime());
		ticket.setFlight(flight);

		Ticket ticket2 = new Ticket();
		ticket2.setPassenger(passenger2);
		ticket2.setTicketNumber(ReservationUtils.generateTicketNumber());
		ticket2.setFlightDate(flight.getDepartureTime());
		ticket2.setFlight(flight);

		Reservation reservation = new Reservation();
		reservation.setCode(ReservationUtils.generateReservationCode());
		reservation.setCustomer(customer);
		reservation.addTickets(Arrays.asList(ticket, ticket2));

		reservationRepository.save(reservation);

		Reservation reservation2 = new Reservation();
		Customer customer2 = new Customer();
		customer2.setEmail("agent@gmail.com");
		customer2.setPhoneNumber("5678900345");
		customer2.setRole(Role.AGENT);
		customerRepository.save(customer2);

		Customer customer3 = new Customer();
		customer3.setEmail("customer@gmail.com");
		customer3.setPhoneNumber("77777777");
		customer3.setRole(Role.CUSTOMER);
		customerRepository.save(customer3);

		Passenger passenger3 = new Passenger();
		passenger3.setFirstName("Salek");
		passenger3.setLastName("Payman");

		Ticket ticket3 = new Ticket();
		ticket3.setPassenger(passenger3);
		ticket3.setTicketNumber(ReservationUtils.generateTicketNumber());
		ticket3.setFlightDate(flight.getDepartureTime());
		ticket3.setFlight(flight);

		reservation2.setCode(ReservationUtils.generateReservationCode());
		reservation2.setAgent(customer2);
		reservation2.setCustomer(customer3);
		reservation2.addTickets(Arrays.asList(ticket3));
		reservationRepository.save(reservation2);
	}
}