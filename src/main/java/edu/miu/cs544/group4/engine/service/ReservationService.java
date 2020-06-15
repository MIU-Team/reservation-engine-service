package edu.miu.cs544.group4.engine.service;

import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs544.group4.engine.model.Customer;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.model.Passenger;
import edu.miu.cs544.group4.engine.model.Reservation;
import edu.miu.cs544.group4.engine.service.response.PassengerReservationResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResponse;

import java.util.List;

public interface ReservationService extends BaseReadWriteService<ReservationResponse, Reservation, Integer> {
  ReservationResponse makeReservation(Customer customer, List<Flight> flights) throws ResourceNotFoundException;

  ReservationResponse confirmReservation(String reservationCode, List<Passenger> passengers) throws ResourceNotFoundException;

  List<ReservationResponse> getAllReservationsByEmail(String email);

  List<PassengerReservationResponse> getAllCustomerPassengersAndTheirReservations(String email);

  ReservationResponse getReservationByCode(String reservationCode);

  ReservationResponse cancelReservationByCode(String reservationCode) throws ResourceNotFoundException;
}
