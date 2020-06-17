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
public class AirlineResponseMapper extends BaseMapper<Airline, AirlineResponse> {

  @Autowired
  public AirlineResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, Airline.class, AirlineResponse.class);
  }
}
