package edu.miu.cs544.group4.engine.service;

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
import edu.miu.cs544.group4.engine.service.request.CancelReservationRequest;
import edu.miu.cs544.group4.engine.service.request.ConfirmReservationRequest;
import edu.miu.cs544.group4.engine.service.request.ReservationRequest;
import edu.miu.cs544.group4.engine.service.response.AddressResponse;
import edu.miu.cs544.group4.engine.service.response.PassengerReservationResponse;
import edu.miu.cs544.group4.engine.service.response.PriorDepartureReservationResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResultResponse;
import edu.miu.cs544.group4.engine.util.DateUtils;
import edu.miu.cs544.group4.engine.util.ReservationUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    public List<ReservationResponse> getAgentReservationsByEmail(String email) {
        return convertEntityListToResponseList(reservationRepository.getAllByAgent_Email(email));
    }

    @Override
    public List<PassengerReservationResponse> getAllAgentPassengersAndTheirReservations(String email) {
        return reservationRepository.getAllByAgent_Email(email)
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
    public ReservationResponse cancelReservationByCode(CancelReservationRequest request) {
        Reservation reservation = Optional
            .ofNullable(reservationRepository.findByCode(request.getReservationCode()))
            .orElseThrow(() -> new IllegalArgumentException("Reservation code is invalid"));

        if (!request.getEmail().equals(reservation.getCustomer().getEmail())) {
            throw new BusinessException("Cannot cancel the Reservation that was not made by you");
        }

        if (!reservation.canCancel()) {
            throw new BusinessException("Cannot cancel the Reservation because it was already Canceled");
        }

        reservation.cancel();
        return convertEntityToResponse(reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponse agentCancelReservationByCode(CancelReservationRequest request) {
        Reservation reservation = Optional
            .ofNullable(reservationRepository.findByCode(request.getReservationCode()))
            .orElseThrow(() -> new IllegalArgumentException("Reservation code is invalid"));

        Optional.ofNullable(reservation.getAgent()).map(Customer::getEmail)
            .filter(email -> email.equals(request.getEmail()))
            .orElseThrow(() -> new BusinessException("Cannot confirm a reservation that was not made by you"));


        if (!reservation.canCancel()) {
            throw new BusinessException("Cannot cancel the Reservation because it was already Canceled");
        }

        reservation.cancel();
        return convertEntityToResponse(reservationRepository.save(reservation));
    }

    @Override
    public ReservationResultResponse makeReservation(ReservationRequest request) {
        List<String> invalidFlights = new ArrayList<>();
        Set<Flight> flights = new HashSet<>();

        prepareReservationData(request, flights, invalidFlights);

        // Retrieves existing Customer, otherwise create new Customer based on given information
        Customer realCustomer = Optional
            .ofNullable(customerRepository.findByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber()))
            .orElseGet(() -> getOrCreateCustomer(request));
        Reservation reservation = new Reservation();
        reservation.setCustomer(realCustomer);
        reservation.setFlights(flights);
        reservation.setCode(ReservationUtils.generateReservationCode());
        return convertToReservationResultResponse(reservationRepository.save(reservation), invalidFlights);
    }

    @Override
    public ReservationResultResponse makeAgentReservation(ReservationRequest request) {
        List<String> invalidFlights = new ArrayList<>();
        Set<Flight> flights = new HashSet<>();

        Optional.ofNullable(request.getCustomerRequest())
            .filter(c -> !StringUtils.isEmpty(c.getName()))
            .filter(c -> !StringUtils.isEmpty(c.getEmail()) || !StringUtils.isEmpty(c.getPhoneNumber()))
            .orElseThrow(() -> new IllegalArgumentException("Customer information is required"));

        prepareReservationData(request, flights, invalidFlights);

        Customer realAgent = Optional
            .ofNullable(customerRepository.findByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber()))
            .filter(Customer::isAgent)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Agent email/phone number"));

        // Retrieves existing Customer, otherwise create new Customer based on given information
        Customer realCustomer = Optional
            .ofNullable(customerRepository.findByEmailOrPhoneNumber(
                request.getCustomerRequest().getEmail(),
                request.getCustomerRequest().getPhoneNumber()))
            .orElseGet(() -> getOrCreateCustomer(request));
        Reservation reservation = new Reservation();
        reservation.setCustomer(realCustomer);
        reservation.setAgent(realAgent);
        reservation.setFlights(flights);
        reservation.setCode(ReservationUtils.generateReservationCode());
        return convertToReservationResultResponse(reservationRepository.save(reservation), invalidFlights);
    }

    @Override
    public ReservationResponse confirmReservation(ConfirmReservationRequest confirmReservationRequest) {
        Reservation reservation = Optional
            .ofNullable(reservationRepository.findByCode(confirmReservationRequest.getReservationCode()))
            .orElseThrow(() -> new IllegalArgumentException("Incorrect Reservation Code"));

        if (confirmReservationRequest.getEmail() != reservation.getCustomer().getEmail()) {
            throw new BusinessException("Cannot confirm a reservation that was not made by you");
        }

        if (CollectionUtils.isEmpty(confirmReservationRequest.getPassengers())) {
            throw new BusinessException("Cannot confirm a reservation with no passengers");
        }

        return generateReservationResponse(reservation, confirmReservationRequest.getPassengers());
    }

    @Override
    public ReservationResponse agentConfirmReservation(ConfirmReservationRequest confirmReservationRequest) {
        Reservation reservation = Optional
            .ofNullable(reservationRepository.findByCode(confirmReservationRequest.getReservationCode()))
            .orElseThrow(() -> new IllegalArgumentException("Incorrect Reservation Code"));

        Optional.ofNullable(reservation.getAgent()).map(Customer::getEmail)
            .filter(email -> email.equals(confirmReservationRequest.getEmail()))
            .orElseThrow(() -> new BusinessException("Cannot confirm a reservation that was not made by you"));


        if (CollectionUtils.isEmpty(confirmReservationRequest.getPassengers())) {
            throw new BusinessException("Cannot confirm a reservation with no passengers");
        }

        return generateReservationResponse(reservation, confirmReservationRequest.getPassengers());
    }

    @Override
    public List<PriorDepartureReservationResponse> get24HoursPriorDepartureReservations() {
        return reservationRepository
            .getAllReservationsMatchTheDepartureTime(DateUtils.generateFutureDate(1))
            .stream()
            .map(reservation -> new PriorDepartureReservationResponse(reservation.getCode(), reservation.getCustomer().getEmail()))
            .collect(Collectors.toList());
    }

    /**
     * All support methods should go here
     */

    private ReservationResponse generateReservationResponse(Reservation reservation, List<Passenger> passengers) {
        List<Ticket> tickets = reservation.getFlights()
                .stream()
                .map(flight -> generateTickets(flight, passengers))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        if (!reservation.canConfirm()) {
            throw new BusinessException("Cannot confirm reservation because it is either confirmed or cancelled");
        }
        reservation.addTickets(tickets);
        reservation.confirm();
        reservationRepository.save(reservation);
        return convertEntityToResponse(reservation);
    }

    private List<Ticket> generateTickets(Flight flight, List<Passenger> passengers) {
        // check if the number of available seats >= number of passengers who want to buy tickets
        if (!flight.canBookFlight(passengers.size())) {
            throw new BusinessException("We only have " + flight.getAvailableSeats() + " seats available");
        }

        List<Ticket> tickets = passengers
                .stream()
                .map(p -> {
                    Ticket ticket = new Ticket();
                    ticket.setFlightDate(flight.getDepartureTime());
                    ticket.setPassenger(p);
                    ticket.setTicketNumber(ReservationUtils.generateTicketNumber());
                    return ticket;
                }).collect(Collectors.toList());

        // reduce the seats on this flight by number of passengers who have bought tickets
        flight.updateAvailableSeats(passengers.size());
        return tickets;
    }

    private List<PassengerReservationResponse> convertPassengerToPassengerReservationResponse(Reservation reservation) {
        return reservation.getTickets().stream()
            .map(Ticket::getPassenger)
            .map(passenger -> {
                PassengerReservationResponse p = new PassengerReservationResponse();
                p.setFirstName(passenger.getFirstName());
                p.setLastName(passenger.getLastName());
                if (passenger.getAddress() != null) {
                    AddressResponse addr = new AddressResponse();
                    BeanUtils.copyProperties(passenger.getAddress(), addr);
                    p.setAddress(addr);
                }
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

    private void prepareReservationData(ReservationRequest request, Set<Flight> flights, List<String> invalidFlights) {
        if (CollectionUtils.isEmpty(request.getFlightNumbers()))
            throw new IllegalArgumentException("Flight number(s) are required");

        if (StringUtils.isEmpty(request.getEmail()) && StringUtils.isEmpty(request.getPhoneNumber()))
            throw new IllegalArgumentException("Email/Phone Number is required");

        flights.addAll(
            request.getFlightNumbers()
                .stream()
                .map(flightNo -> {
                    Flight flight = flightRepository.findTopByFlightNumberOrderByDepartureTimeDesc(flightNo);
                    if (flight == null) invalidFlights.add(flightNo);
                    return flight;
                })
                .filter(f -> f != null)
                .collect(Collectors.toSet())
        );

        if (flights.isEmpty())
            throw new IllegalArgumentException("Invalid Flight number");
    }

    private Customer getOrCreateCustomer(ReservationRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setEmail(request.getEmail());
        return customerRepository.save(customer);
    }
}
