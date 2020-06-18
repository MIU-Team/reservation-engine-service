package edu.miu.cs544.group4.engine;

import edu.miu.cs544.group4.engine.model.Address;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.model.History;
import edu.miu.cs544.group4.engine.model.Passenger;
import edu.miu.cs544.group4.engine.model.Reservation;
import edu.miu.cs544.group4.engine.model.Ticket;
import edu.miu.cs544.group4.engine.repository.AirlineRepository;
import edu.miu.cs544.group4.engine.repository.AirportRepository;
import edu.miu.cs544.group4.engine.repository.FlightRepository;
import edu.miu.cs544.group4.engine.repository.ReservationRepository;
import edu.miu.cs544.group4.engine.util.DateUtils;
import edu.miu.cs544.group4.engine.util.ReservationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * @author knguyen93
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class RepositoryTest {

  @Autowired
  ReservationRepository reservationRepository;
  @Autowired
  FlightRepository flightRepository;
  @Autowired
  AirlineRepository airlineRepository;
  @Autowired
  AirportRepository airportRepository;

  @Test
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

    Address address4 = new Address();
    address4.setCity("Somewhere 4");
    address4.setState("Some State");
    address4.setZip("2343");
    address4.setStreet("6789 Blablala");

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

    Passenger passenger = new Passenger();
    passenger.setAddress(address4);
    passenger.setFirstName("Mike");
    passenger.setLastName("John");

    Passenger passenger2 = new Passenger();
    passenger2.setAddress(address4);
    passenger2.setFirstName("Samuel");
    passenger2.setLastName("Bwambale");

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
    reservation.setCustomerEmail("customer@gmail.com");
    reservation.setTickets(Arrays.asList(ticket, ticket2));

    reservationRepository.save(reservation);

  }
}
