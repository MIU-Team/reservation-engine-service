package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;

public interface FlightService extends BaseReadWriteService<FlightResponse, Flight, Integer> {
  FlightResponse create(Flight flight);
}
