package edu.miu.cs544.group4.engine.controller;

import edu.miu.cs544.group4.engine.service.AirlineService;
import edu.miu.cs544.group4.engine.service.AirportService;
import edu.miu.cs544.group4.engine.service.FlightService;
import edu.miu.cs544.group4.engine.service.request.FlightRequest;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import edu.miu.cs544.group4.engine.service.response.AirportResponse;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public List<FlightResponse> getAllFlightsOnADate(@RequestBody FlightRequest flightRequest) {
    return flightService.getFlightsOnRouteAndDate(flightRequest);
  }

  @PostMapping("/all-flights-on-route")
  public List<FlightResponse> getAllFlightsOnRoute(@RequestBody FlightRequest flightRequest) {
    return flightService.getFlightsOnRoute(flightRequest);
  }
}
