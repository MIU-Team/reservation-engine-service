package edu.miu.cs544.group4.engine.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.model.Passenger;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends BaseRepository<Passenger, Integer> {
}
