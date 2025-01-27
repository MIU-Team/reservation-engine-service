package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.repository.AirlineRepository;
import edu.miu.cs544.group4.engine.repository.AirportRepository;
import edu.miu.cs544.group4.engine.repository.FlightRepository;
import edu.miu.cs544.group4.engine.service.request.FlightRequest;
import edu.miu.cs544.group4.engine.service.request.SaveFlightRequest;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;
import edu.miu.cs544.group4.engine.util.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class FlightServiceImpl extends BaseReadWriteServiceImpl<FlightResponse, Flight, Integer>
		implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private AirlineRepository airlineRepository;

	@Autowired
	private AirportRepository airportRepository;

	@Override
	public FlightResponse create(SaveFlightRequest request) {
		Flight newFlight = new Flight();
		newFlight = prepareFlightData(request, newFlight);
		return convertEntityToResponse(baseRepository.save(newFlight));
	}

	@Override
	public FlightResponse update(int id, SaveFlightRequest request) {
		Flight flight = flightRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid Flight information. " + request.toString()));
		flight = prepareFlightData(request, flight);
		return convertEntityToResponse(baseRepository.save(flight));
	}

	@Override
	public List<FlightResponse> getFlightsOnRouteAndDate(FlightRequest flightRequest) {
		Date fromDate = DateUtils.formatDate(flightRequest.getDate());
		Date toDate = DateUtils.fromDateAfter(fromDate, 1);
		return convertEntityListToResponseList(
			flightRepository.getFlightsOnRouteAndDate(
				flightRequest.getOriginCode(),
				flightRequest.getDestinationCode(), fromDate, toDate)
		);
	}

	@Override
	public List<FlightResponse> getFlightsOnRoute(FlightRequest flightRequest) {
		return convertEntityListToResponseList(
				flightRepository.getFlightsOnRoute(
					flightRequest.getOriginCode(),
					flightRequest.getDestinationCode())
		);
	}

	@Override
	public String deleteFlight(int id) {
		 flightRepository.deleteById(id);
		return "Flight Deleted Successfully";
	}

	private Flight prepareFlightData(SaveFlightRequest request, Flight flight) {
		flight.setCapacity(request.getCapacity());
		flight.setAvailableSeats(request.getAvailableSeats());
		flight.setFlightNumber(request.getFlightNumber());
		flight.setDepartureTime(request.getDepartureTime());
		flight.setArrivalTime(request.getArrivalTime());
		flight.setAirline(airlineRepository.findById(request.getAirlineId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid Airline")));
		flight.setOrigin(airportRepository.findById(request.getOriginAirportId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid Origin Airport")));
		flight.setDestination(airportRepository.findById(request.getDestinationAirportId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid Destination Airport")));
		return flight;
	}

	@Override
	public List<FlightResponse> getFlightByNumber(String flightNumber) {
		return convertEntityListToResponseList(
				flightRepository.findByFlightNumber(flightNumber));
	}
}
