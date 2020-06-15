package edu.miu.cs544.group4.engine.service;

import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.exception.BusinessException;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
    public List<ReservationResponse> getAllReservationsByEmail(String email) {
        return convertEntityListToResponseList(reservationRepository.getAllReservationByCustomerEmail(email));
    }

    @Override
    public ReservationResponse getReservationByCode(String reservationCode) {
        return convertEntityToResponse(reservationRepository.findByCode(reservationCode));
    }

    @Override
    public ReservationResponse cancelReservationByCode(String reservationCode) throws ResourceNotFoundException {
        Reservation reservation = Optional
            .ofNullable(reservationRepository.findByCode(reservationCode))
            .orElseThrow(ResourceNotFoundException::new);

        if (!reservation.canCancel()) {
            throw new BusinessException("Cannot cancel the Reservation because it was already Canceled");
        }

        reservation.cancel();
        return convertEntityToResponse(reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponse makeReservation(Customer customer, List<Flight> flights) throws ResourceNotFoundException {
        Customer realCustomer = Optional
            .ofNullable(customerRepository.findByEmailOrPhoneNumber(customer.getEmail(), customer.getPhoneNumber()))
            .orElseGet(() -> Arrays.asList(customerRepository.save(customer)))
            .get(0);
        Reservation reservation = new Reservation();
        reservation.setCustomer(realCustomer);
        reservation.setFlights(flights);
        reservation.setCode(ReservationUtils.generateReservationCode());
        return convertEntityToResponse(reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponse confirmReservation(String reservationCode, List<Passenger> passengers) throws ResourceNotFoundException {
        Reservation reservation = Optional
            .ofNullable(reservationRepository.findByCode(reservationCode))
            .orElseThrow(ResourceNotFoundException::new);

        List<Ticket> tickets = reservation.getFlights()
            .stream()
            .map(flight -> generateTickets(flight, passengers))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        reservation.addTickets(tickets);
        reservationRepository.save(reservation);
        return convertEntityToResponse(reservation);
    }

    private List<Ticket> generateTickets(Flight flight, List<Passenger> passengers) {
        return passengers
            .stream()
            .map(p -> {
                Ticket ticket = new Ticket();
                ticket.setFlightDate(flight.getDepartureTime());
                ticket.setPassenger(p);
                ticket.setTicketNumber(ReservationUtils.generateTicketNumber());
                return ticket;
            }).collect(Collectors.toList());
    }
}
