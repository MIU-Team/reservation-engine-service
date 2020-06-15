package edu.miu.cs544.group4.engine.controller;

import edu.miu.cs544.group4.engine.model.Reservation;
import edu.miu.cs544.group4.engine.service.ReservationService;
import edu.miu.cs544.group4.engine.service.response.ReservationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author knguyen93
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping("/reservations/{email}")
  public List<ReservationResponse> getAllReservations(@PathVariable String email) {
    return reservationService.getAllReservationsByEmail(email);
  }

  @GetMapping("/reservation/{code}")
  public ReservationResponse getReservation(@PathVariable String reservationCode) {
    return reservationService.getReservationByCode(reservationCode);
  }
}
