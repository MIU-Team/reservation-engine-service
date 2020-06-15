package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteService;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import edu.miu.cs544.group4.engine.service.response.AirportResponse;

public interface AirportService extends BaseReadWriteService<AirportResponse, Airport, Integer> {
	AirportResponse create(Airport airport);

	boolean deleteAirport(int id);
}
