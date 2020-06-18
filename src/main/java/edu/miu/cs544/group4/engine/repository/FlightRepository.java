package edu.miu.cs544.group4.engine.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs544.group4.engine.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends BaseRepository<Flight, Integer> {
  List<Flight> getFlightsOnRouteAndDate(String originCode, String destinationCode, Date fromDate, Date toDate);

  List<Flight> getFlightsOnRoute(String originCode, String destinationCode);
  Flight findTopByFlightNumberOrderByDepartureTimeDesc(String flightNumber);
  List<Flight> findByFlightNumber(String flightNumber);
  
}
