package edu.miu.cs544.group4.engine.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs544.group4.engine.model.Reservation;
import edu.miu.cs544.group4.engine.service.response.ReservationResponse;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author knguyen93
 */
@Component
public class ReservationResponseMapper extends BaseMapper<Reservation, ReservationResponse> {

  @Autowired
  public ReservationResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, Reservation.class, ReservationResponse.class);
  }
}
