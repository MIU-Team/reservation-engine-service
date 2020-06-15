package edu.miu.cs544.group4.engine.service;

import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.repository.FlightRepository;
import edu.miu.cs544.group4.engine.service.request.FlightRequest;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;
import edu.miu.cs544.group4.engine.util.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class FlightServiceImpl extends BaseReadWriteServiceImpl<FlightResponse, Flight, Integer> implements FlightService {

  @Autowired
  private FlightRepository flightRepository;

  @Override
  public FlightResponse create(Flight flight) {
    return convertEntityToResponse(baseRepository.save(flight));
  }

  @Override
  public List<FlightResponse> getFlightsOnRouteAndDate(FlightRequest flightRequest) {
    Date fromDate = DateUtils.formatDate(flightRequest.getDate());
    Date toDate = DateUtils.fromDateAfter(fromDate, 1);
    log.info(flightRequest.toString());
    log.info("fromDate=" + fromDate + ",toDate=  "+ toDate);
    return convertEntityListToResponseList(
        flightRepository.getFlightsOnRouteAndDate(
        flightRequest.getOriginCode(),
        flightRequest.getDestinationCode(),
        fromDate, toDate)
    );
  }

  @Override
  public List<FlightResponse> getFlightsOnRoute(FlightRequest flightRequest) {
    log.info(flightRequest.toString());
    return convertEntityListToResponseList(
        flightRepository.getFlightsOnRoute(
        flightRequest.getOriginCode(),
        flightRequest.getDestinationCode()
    ));
  }

  	@Override
	public boolean deleteFlight(int id) {
		// TODO Auto-generated method stub
  		flightRepository.deleteById(id);
		return true;
	}
}
