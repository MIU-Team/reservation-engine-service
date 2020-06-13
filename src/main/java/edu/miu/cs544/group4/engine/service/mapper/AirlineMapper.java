package edu.miu.cs544.group4.engine.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs544.group4.engine.model.Airline;
import edu.miu.cs544.group4.engine.service.response.AirlineResponse;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author knguyen93
 */
@Component
public class AirlineMapper extends BaseMapper<AirlineResponse, Airline> {

  @Autowired
  public AirlineMapper(MapperFactory mapperFactory) {
    super(mapperFactory, AirlineResponse.class, Airline.class);
  }
}
