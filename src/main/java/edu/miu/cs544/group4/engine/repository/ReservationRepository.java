package edu.miu.cs544.group4.engine.repository;

import edu.miu.common.repository.BaseRepository;
import edu.miu.cs544.group4.engine.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation, Integer> {
  List<Reservation> getAllReservationByCustomerEmail(String email);
  Reservation findByCode(String code);
  List<Reservation> getAllByAgent_Email(String email);
  List<Reservation> getAllReservationsMatchTheDepartureTime(Date departureTime);
}
