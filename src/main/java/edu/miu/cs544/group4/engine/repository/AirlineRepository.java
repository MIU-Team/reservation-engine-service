package edu.miu.cs544.group4.engine.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.model.Airport;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends BaseRepository<Airline, Integer> {
}
