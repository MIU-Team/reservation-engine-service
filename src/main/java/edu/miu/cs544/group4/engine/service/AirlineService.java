package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;

public interface AirlineService extends BaseReadWriteService<AirlineResponse, Airline, Integer> {
  AirlineResponse create(Airline airline);
}
