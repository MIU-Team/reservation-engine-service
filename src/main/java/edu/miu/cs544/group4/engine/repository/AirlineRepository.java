package edu.miu.cs544.group4.engine.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs544.group4.engine.model.Airline;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends BaseRepository<Airline, Integer> {
  List<Airline> findAirlinesOutOfAnAirport(String airportCode);
  
  
//Getting Passenger Data Using different feature
public List<Airline> findByName(String name);



//Deleting Passenger Data Using Different feature
public String deleteByName(String name);

public default void updateAirline(Integer id, Airline airline) {
	Airline u = findById(id).get();
	u.setName(airline.getName());
	u.setCode(airline.getCode());
}
}
