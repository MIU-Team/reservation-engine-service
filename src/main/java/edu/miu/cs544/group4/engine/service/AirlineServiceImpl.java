package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import edu.miu.cs544.group4.engine.service.response.AirportResponse;
import org.springframework.stereotype.Service;

@Service
public class AirlineServiceImpl extends BaseReadWriteServiceImpl<AirlineResponse, Airline, Integer> implements AirlineService {
}
