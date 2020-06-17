package edu.miu.cs544.group4.engine.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.service.AirlineService;
import edu.miu.cs544.group4.engine.service.AirportService;
import edu.miu.cs544.group4.engine.service.FlightService;
import edu.miu.cs544.group4.engine.service.request.SaveFlightRequest;
import edu.miu.cs544.group4.engine.service.response.AirportResponse;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;

@RestController
@RequestMapping("/admin/")
public class AdminController extends BaseReservationController {

	// Properties
	@Autowired
	AirportService airportService;

	@Autowired
	FlightService flightService;

	@Autowired
	AirlineService airlineService;

	// Airline APIs
	@GetMapping("/airline/view/all")



	public List<Airline> allAirlines() throws ResourceNotFoundException{

		return airlineService.getAllAirline();
	}

	@GetMapping("/airline/view/id/{id}")



	public Airline getAirline(@PathVariable Integer id) throws ResourceNotFoundException{

		return airlineService.getAirlineById(id);
	}

	@GetMapping("/airline/view/name")
	public List<Airline> getByNameAirline(@RequestParam String name) throws ResourceNotFoundException{
		return airlineService.getAirlineByName(name);
	}

	// Saving Starts Here
	@PostMapping(value = "/airline/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Airline saveAirline(@Valid @RequestBody Airline airline) throws ResourceNotFoundException{
		airlineService.saveAirline(airline);
		return airline;
	}

	// Deleting Starts here
	@DeleteMapping("/airline/delete/id/{id}")
	public String deleteAirlineById(@PathVariable Integer id) throws ResourceNotFoundException{
		airlineService.removeAirlineById(id);
		return "The Airline with Id " + id + " is deleted successfully";
	}

	@DeleteMapping("/airline/delete")


	public String deleteAllAirlines() throws ResourceNotFoundException{

		airlineService.removeAll();
		return "All The Airline Data are Successfully Deleted";
	}

	@DeleteMapping("/airline/delete/name")
	public String deleteAirlineByFName(@RequestParam String name) throws ResourceNotFoundException {
		airlineService.removeAirlineByName(name);
		return "The Airline with the name " + name + " is deleted successfully";
	}

	// Updating Starts here
	@PutMapping("/airline/update/id/{id}")
	public String updateAirlineById(@PathVariable Integer id,@Valid @RequestBody Airline airline) throws ResourceNotFoundException{
		airlineService.updateAirlineById(id, airline);
		return "The Airline with Id " + id + " is Updated successfully";
	}

	
	// Airport APIs
	@GetMapping("airport/list")
	public List<AirportResponse> listAirports() {
		return airportService.findAll();
	}

	@GetMapping("airport/{id}")
	public AirportResponse getById(@PathVariable int id) throws ResourceNotFoundException {
		return airportService.findById(id);
	}

	@GetMapping("airport/view/{name}")
	public AirportResponse getByName(@PathVariable String name) throws ResourceNotFoundException {
		return airportService.getAirportByName(name);
	}

	@RequestMapping(method = RequestMethod.POST, name = "airport/create")
	public AirportResponse CreateAirport(@Valid @RequestBody Airport airport) {
		return airportService.create(airport);
	}

	@PostMapping("airport/update/{id}")
	public AirportResponse UpdateAirport(@PathVariable int id,@Valid @RequestBody AirportResponse request)
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
	public FlightResponse CreateFlight(@Valid @RequestBody SaveFlightRequest flight) {
		return flightService.create(flight);
	}

	@PostMapping("flight/update/{id}")
	public FlightResponse UpdateFlight(@Valid @PathVariable int id, @RequestBody SaveFlightRequest request)
			throws ResourceNotFoundException {
		return flightService.update(id, request);
	}

	@GetMapping("flight/delete/{id}")
	public boolean DeleteFlight(@PathVariable int id) throws ResourceNotFoundException {
		return flightService.deleteFlight(id);
	}
    
	
}
