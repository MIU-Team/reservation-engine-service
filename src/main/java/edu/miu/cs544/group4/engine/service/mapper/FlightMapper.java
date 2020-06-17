package edu.miu.cs544.group4.engine.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs544.group4.engine.model.Flight;
import edu.miu.cs544.group4.engine.service.response.FlightResponse;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author knguyen93
 */
@Component
public class FlightMapper extends BaseMapper<FlightResponse, Flight> {

  @Autowired
  public FlightMapper(MapperFactory mapperFactory) {
    super(mapperFactory, FlightResponse.class, Flight.class);
  }
}
