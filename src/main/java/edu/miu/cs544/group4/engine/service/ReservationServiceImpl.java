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
import edu.miu.cs544.group4.engine.repository.FlightRepository;
import edu.miu.cs544.group4.engine.repository.PassengerRepository;
import edu.miu.cs544.group4.engine.repository.ReservationRepository;
import edu.miu.cs544.group4.engine.repository.TicketRepository;
import edu.miu.cs544.group4.engine.service.mapper.FlightResponseMapper;
import edu.miu.cs544.group4.engine.service.request.ReservationRequest;
import edu.miu.cs544.group4.engine.service.response.AddressResponse;
import edu.miu.cs544.group4.engine.service.response.PassengerReservationResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResultResponse;
import edu.miu.cs544.group4.engine.util.ReservationUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightResponseMapper flightResponseMapper;

    @Override
    public List<ReservationResponse> getAllReservationsByEmail(String email) {
        return convertEntityListToResponseList(reservationRepository.getAllReservationByCustomerEmail(email));
    }

    @Override
    public List<PassengerReservationResponse> getAllCustomerPassengersAndTheirReservations(String email) {
        return reservationRepository.getAllReservationByCustomerEmail(email)
                .stream()
                .map(this::convertPassengerToPassengerReservationResponse)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
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
    public ReservationResultResponse makeReservation(ReservationRequest request) {
        if (CollectionUtils.isEmpty(request.getFlightNumbers()))
            throw new IllegalArgumentException("Flight number(s) are required");

        if (StringUtils.isEmpty(request.getEmail()) || StringUtils.isEmpty(request.getPhoneNumber()))
            throw new IllegalArgumentException("Email/Phone Number is required");

        // Retrieves existing Customer, otherwise create new Customer based on given information
        Customer realCustomer = Optional
            .ofNullable(customerRepository.findByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber()))
            .orElseGet(() -> {
                Customer customer = new Customer();
                customer.setName(request.getName());
                customer.setPhoneNumber(request.getPhoneNumber());
                customer.setEmail(request.getEmail());
                return Arrays.asList(customerRepository.save(customer));
            })
            .get(0);
        List<String> invalidFlights = new ArrayList<>();
        List<Flight> flights = request.getFlightNumbers()
            .stream()
            .map(flightNo -> {
                Flight flight = flightRepository.findByFlightNumber(flightNo);
                if (flight != null) invalidFlights.add(flightNo);
                return flight;
            })
            .filter(f -> f != null)
            .collect(Collectors.toList());
        Reservation reservation = new Reservation();
        reservation.setCustomer(realCustomer);
        reservation.setFlights(flights);
        reservation.setCode(ReservationUtils.generateReservationCode());
        reservationRepository.save(reservation);
        return convertToReservationResultResponse(reservation, invalidFlights);
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

    /**
     * All support methods should go here
     */

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

    private List<PassengerReservationResponse> convertPassengerToPassengerReservationResponse(Reservation reservation) {
      return reservation.getTickets().stream()
          .map(Ticket::getPassenger)
          .map(passenger -> {
            PassengerReservationResponse p = new PassengerReservationResponse();
            p.setFirstName(passenger.getFirstName());
            p.setLastName(passenger.getLastName());
            AddressResponse addr = new AddressResponse();
            BeanUtils.copyProperties(passenger.getAddress(), addr);
            p.setAddress(addr);
            p.getReservationCodes().add(reservation.getCode());

            return p;
          }).collect(Collectors.toList());
    }

    private ReservationResultResponse convertToReservationResultResponse(Reservation reservation, List<String> invalidFlights) {
        ReservationResultResponse response = new ReservationResultResponse();
        response.setCode(reservation.getCode());
        response.setFlights(
            reservation.getFlights()
                .stream()
                .map(flightResponseMapper::map)
                .collect(Collectors.toList())
        );
        response.setReservationTime(reservation.getReservationTime());
        response.setInvalidFlightNumbers(invalidFlights);
        return response;
    }
}
