package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.repository.AirportRepository;
import edu.miu.cs544.group4.engine.service.response.AirportResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportServiceImpl extends BaseReadWriteServiceImpl<AirportResponse, Airport, Integer>
		implements AirportService {

	@Autowired
	AirportRepository airportRepository;

	@Override
	public AirportResponse create(Airport airport) {
		return convertEntityToResponse(baseRepository.save(airport));
	}

	@Override
	public boolean deleteAirport(int id) {
		airportRepository.deleteById(id);
		return true;
	}
}
