package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.model.Customer;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.model.Passenger;
import edu.miu.cs544.group4.engine.model.Reservation;
import edu.miu.cs544.group4.engine.model.Ticket;
import edu.miu.cs544.group4.engine.repository.CustomerRepository;
import edu.miu.cs544.group4.engine.repository.PassengerRepository;
import edu.miu.cs544.group4.engine.repository.ReservationRepository;
import edu.miu.cs544.group4.engine.repository.TicketRepository;
import edu.miu.cs544.group4.engine.service.response.ReservationResponse;
import edu.miu.cs544.group4.engine.util.ReservationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl extends BaseReadWriteServiceImpl<ReservationResponse, Reservation, Integer> implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void makeReservation(Customer customer, List<Passenger> passengers, List<Flight> flights) {
        List<Ticket> tickets = flights.stream()
                .map(flight -> flightToTickets(flight, passengers))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // persits the Tickets
        // persist the Reservation

        Reservation reservation = new Reservation();
        reservation.setTickets(tickets);
        reservation.setCode(ReservationUtils.generateReservationCode());
        reservation.setCustomer(customer);

        reservationRepository.save(reservation);
    }

    private List<Ticket> flightToTickets(Flight flight, List<Passenger> passengers) {
        return passengers.stream().map(p -> {
            Ticket ticket = new Ticket();
            ticket.setFlightDate(flight.getDepartureTime());
            ticket.setPassenger(p);
            ticket.setTicketNumber(ReservationUtils.generateTicketNumber());
            return ticket;
        }).collect(Collectors.toList());
    }

}
