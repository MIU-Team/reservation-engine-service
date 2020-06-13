package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import org.springframework.stereotype.Service;

@Service
public class AirlineServiceImpl extends BaseReadWriteServiceImpl<AirlineResponse, Airline, Integer> implements AirlineService {
  @Override
  public AirlineResponse create(Airline airline) {
    return convertEntityToResponse(baseRepository.save(airline));
  }
}
