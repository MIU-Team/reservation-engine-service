package edu.miu.cs544.group4.engine.controller;

import edu.miu.cs544.group4.engine.configuration.CurrentUser;
import edu.miu.cs544.group4.engine.service.ReservationService;
import edu.miu.cs544.group4.engine.service.request.CancelReservationRequest;
import edu.miu.cs544.group4.engine.service.request.ConfirmReservationRequest;
import edu.miu.cs544.group4.engine.service.request.ReservationRequest;
import edu.miu.cs544.group4.engine.service.response.ReservationResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author knguyen93
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController extends BaseReservationController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping("/reservations")
  public List<ReservationResponse> getAllReservations(HttpServletRequest request) {
    return reservationService.getAllReservationsByEmail(CurrentUser.getEmail(request));
  }

  @GetMapping("/reservation/{reservationCode}")
  public ReservationResponse getReservation(@PathVariable String reservationCode) {
    return reservationService.getReservationByCode(reservationCode);
  }

  @PutMapping("/reservations")
  public ReservationResultResponse makeReservation(@Valid @RequestBody ReservationRequest reservationRequest,
                                                   HttpServletRequest request) {
    reservationRequest.setEmail(CurrentUser.getEmail(request));request.getHeaderNames();
    return reservationService.makeReservation(reservationRequest);
  }

  @PostMapping("/reservations/confirm")
  public ReservationResponse confirmReservation(@Valid @RequestBody ConfirmReservationRequest reservationRequest,
                                                HttpServletRequest request) {
    reservationRequest.setEmail(CurrentUser.getEmail(request));
    return reservationService.confirmReservation(reservationRequest);
  }

  @PostMapping("/reservations/cancel")
  public ReservationResponse cancelReservation(@Valid @RequestBody CancelReservationRequest reservationRequest,
                                               HttpServletRequest request) {
    reservationRequest.setEmail(CurrentUser.getEmail(request));
    return reservationService.cancelReservationByCode(reservationRequest);
  }
}
