package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;

import java.util.List;
import java.util.Optional;

public interface AirlineService extends BaseReadWriteService<AirlineResponse, Airline, Integer> {
  AirlineResponse create(Airline airline);

  List<AirlineResponse> findAirlinesOutOfAnAirport(String code);
  
	//The read part of the CRUD
	public List<Airline> getAllAirline();
	public Optional<Airline> getAirlineById(Integer id);
	public List<Airline> getAirlineByName(String fname);

	
	//The save Part of the CRUD
	public void saveAirline(Airline airline);
	
	
	//The Delete Part of the CRUD
	public void removeAirlineById(Integer id);
	public void removeAll();
	public void removeAirlineByName(String name);
	
	
	//The Update Part of the CRUD
public void updateAirlineById(Integer id, Airline airline);

void updateAirlineByName(String name, Airline airline);


}
