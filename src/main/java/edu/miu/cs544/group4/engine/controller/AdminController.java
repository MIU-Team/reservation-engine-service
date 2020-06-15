package edu.miu.cs544.group4.engine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.service.AirportService;
import edu.miu.cs544.group4.engine.service.FlightService;
import edu.miu.cs544.group4.engine.service.response.AirportResponse;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;

@RestController
@RequestMapping("/admin/")
public class AdminController {

	// Test method will be removed later
	@GetMapping("hello")
	public String sayHello() {
		return "Hello World";
	}

	// Properties
	@Autowired
	AirportService airportService;

	@Autowired
	FlightService flightService;
	// Airline APIs

	// Airport APIs
	@GetMapping("airport/list")
	public List<AirportResponse> listAirports() {
		return airportService.findAll();
	}
	
	@GetMapping("airport/{id}")
	public AirportResponse getById(@PathVariable int id)throws ResourceNotFoundException {
		return airportService.findById(id);
	}

	@GetMapping("airport/view/{name}")
	public AirportResponse getByName(@PathVariable String name)throws ResourceNotFoundException {
		return airportService.getAirportByName(name);
	}
	
	@RequestMapping(method = RequestMethod.POST, name = "airport/create")
	public AirportResponse CreateAirport(@RequestBody Airport airport) {
		return airportService.create(airport);
	}

	@PostMapping("airport/update/{id}")
	public AirportResponse UpdateAirport(@PathVariable int id, @RequestBody AirportResponse request)
			throws ResourceNotFoundException {
		return airportService.update(id, request);
	}

	@GetMapping("airport/delete/{id}")
	public boolean DeleteAirport(@PathVariable int id) throws ResourceNotFoundException {
		return airportService.deleteAirport(id);
	}
	
	// Flight APIs
	@GetMapping("flight/list")
	public List<FlightResponse> listFlights() {
		return flightService.findAll();
	}
	
	@PostMapping("flight/create")
	public FlightResponse CreateFlight(@RequestBody Flight flight) {
		return flightService.create(flight);
	}
	
	@PostMapping("flight/update/{id}")
	public FlightResponse UpdateFlight(@PathVariable int id, @RequestBody FlightResponse request)
			throws ResourceNotFoundException {
		return flightService.update(id, request);
	}

	@GetMapping("flight/delete/{id}")
	public boolean DeleteFlight(@PathVariable int id) throws ResourceNotFoundException {
		return flightService.deleteFlight(id);
	}
}
