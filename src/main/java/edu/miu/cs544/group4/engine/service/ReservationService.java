package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs544.group4.engine.model.*;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResponse;

import java.util.List;

public interface ReservationService extends BaseReadWriteService<ReservationResponse, Reservation, Integer> {
    void makeReservation(Customer customer, List<Passenger> passengers, List<Flight> flights);
}
