package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.service.request.FlightRequest;
import edu.miu.cs544.group4.engine.service.request.SaveFlightRequest;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;

import java.util.List;

public interface FlightService extends BaseReadWriteService<FlightResponse, Flight, Integer> {

	FlightResponse create(SaveFlightRequest request);

	FlightResponse update(int id, SaveFlightRequest request);

	List<FlightResponse> getFlightsOnRouteAndDate(FlightRequest flightRequest);

	List<FlightResponse> getFlightsOnRoute(FlightRequest flightRequest);
	List<FlightResponse> getFlightByNumber(String flightNumber);

	String deleteFlight(int id);
}
