package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.model.*;
import edu.miu.cs544.group4.engine.repository.CustomerRepository;
import edu.miu.cs544.group4.engine.repository.PassengerRepository;
import edu.miu.cs544.group4.engine.repository.ReservationRepository;
import edu.miu.cs544.group4.engine.repository.TicketRepository;
import edu.miu.cs544.group4.engine.service.response.ReservationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.TemporalField;
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
        reservation.setCode(generateReservationCode());
        reservation.setCustomer(customer);

        reservationRepository.save(reservation);
    }

    private String generateReservationCode() {
        return "656463";
    }

    private List<Ticket> flightToTickets(Flight flight, List<Passenger> passengers) {
        return passengers.stream().map(p -> {
            Ticket ticket = new Ticket();
            ticket.setFlightDate(flight.getDepartureTime().toLocalDate());
            ticket.setPassenger(p);
            ticket.setTicketNumber(generateTicketNumber());
            return ticket;
        }).collect(Collectors.toList());
    }

    private String generateTicketNumber() {
        return "45356453564535645356";
    }
}
