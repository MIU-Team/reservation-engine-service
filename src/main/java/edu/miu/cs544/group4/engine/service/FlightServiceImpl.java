package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl extends BaseReadWriteServiceImpl<FlightResponse, Flight, Integer> implements FlightService {
}
