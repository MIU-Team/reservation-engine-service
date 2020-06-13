package edu.miu.cs544.group4.engine.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.model.Flight;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends BaseRepository<Flight, Integer> {
}
