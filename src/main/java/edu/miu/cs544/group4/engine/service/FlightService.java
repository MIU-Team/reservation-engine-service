package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.service.request.FlightRequest;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;

import java.util.List;

public interface FlightService extends BaseReadWriteService<FlightResponse, Flight, Integer> {
  FlightResponse create(Flight flight);

  List<FlightResponse> getFlightsOnRouteAndDate(FlightRequest flightRequest);
  List<FlightResponse> getFlightsOnRoute(FlightRequest flightRequest);
}
