package edu.miu.cs544.group4.engine.controller;

import edu.miu.cs544.group4.engine.service.AirlineService;
import edu.miu.cs544.group4.engine.service.AirportService;
import edu.miu.cs544.group4.engine.service.FlightService;
import edu.miu.cs544.group4.engine.service.request.FlightRequest;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import edu.miu.cs544.group4.engine.service.response.AirportResponse;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author knguyen93
 */
@RestController
@RequestMapping("/common")
public class BaseReservationController {
  @Autowired
  private AirportService airportService;
  @Autowired
  private AirlineService airlineService;
  @Autowired
  private FlightService flightService;

  @RequestMapping("/all-airports")
  public List<AirportResponse> getAllAirports() {
    return airportService.findAll();
  }

  @RequestMapping("/all-airlines/{code}")
  public List<AirlineResponse> getAirlinesOutOfAnAirport(@PathVariable String code) {
    return airlineService.findAirlinesOutOfAnAirport(code);
  }

  /**
   * TODO: make sure it works
   *
   * View list of flights between a departure and destination for a date
   */
  @PostMapping("/all-flights-on-date")
  public List<FlightResponse> getAllFlightsOnADate(@Valid @RequestBody FlightRequest flightRequest) {
    return flightService.getFlightsOnRouteAndDate(flightRequest);
  }

  @PostMapping("/all-flights-on-route")
  public List<FlightResponse> getAllFlightsOnRoute(@Valid @RequestBody FlightRequest flightRequest) {
    return flightService.getFlightsOnRoute(flightRequest);
  }
  
  
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
      Map<String, String> errors = new HashMap<>();
   
      ex.getBindingResult().getFieldErrors().forEach(error -> 
          errors.put(error.getField(), error.getDefaultMessage()));
       
      return errors;
  }
}
