package edu.miu.cs544.group4.engine.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs544.group4.engine.model.Airport;
import edu.miu.cs544.group4.engine.service.response.AirportResponse;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author knguyen93
 */
@Component
public class AirportMapper extends BaseMapper<AirportResponse, Airport> {

  @Autowired
  public AirportMapper(MapperFactory mapperFactory) {
    super(mapperFactory, AirportResponse.class, Airport.class);
  }
}
