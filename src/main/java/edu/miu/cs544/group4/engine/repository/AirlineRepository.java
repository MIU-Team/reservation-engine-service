package edu.miu.cs544.group4.engine.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs544.group4.engine.model.Airline;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends BaseRepository<Airline, Integer> {
  List<Airline> findAirlinesOutOfAnAirport(String airportCode);
}
