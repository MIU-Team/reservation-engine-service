package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.repository.AirlineRepository;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl extends BaseReadWriteServiceImpl<AirlineResponse, Airline, Integer> implements AirlineService {

  @Autowired
  private AirlineRepository airlineRepository;

  @Override
  public AirlineResponse create(Airline airline) {
    return convertEntityToResponse(baseRepository.save(airline));
  }

  @Override
  public List<AirlineResponse> findAirlinesOutOfAnAirport(String code) {
    return convertEntityListToResponseList(airlineRepository.findAirlinesOutOfAnAirport(code));
  }
}
