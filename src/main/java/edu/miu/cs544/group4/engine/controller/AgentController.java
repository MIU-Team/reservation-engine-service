package edu.miu.cs544.group4.engine.controller;

import edu.miu.cs544.group4.engine.configuration.CurrentUser;
import edu.miu.cs544.group4.engine.service.ReservationService;
import edu.miu.cs544.group4.engine.service.request.AgentReservationRequest;
import edu.miu.cs544.group4.engine.service.request.CancelReservationRequest;
import edu.miu.cs544.group4.engine.service.request.ConfirmReservationRequest;
import edu.miu.cs544.group4.engine.service.request.ReservationRequest;
import edu.miu.cs544.group4.engine.service.response.PassengerReservationResponse;
        import edu.miu.cs544.group4.engine.service.response.ReservationResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author knguyen93
 */
@RestController
@RequestMapping("/agent")
public class AgentController extends BaseReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public List<ReservationResponse> getAllReservations(HttpServletRequest request)  {
        return reservationService.getAgentReservationsByEmail(CurrentUser.getEmail(request));
    }

    @GetMapping("/passenger-reservations/{email}")
    public List<PassengerReservationResponse> getAllAgentPassengersAndTheirReservations(@PathVariable String email) {
        return reservationService.getAllAgentPassengersAndTheirReservations(email);
    }

    @PutMapping("/reservations")
    public ReservationResultResponse makeReservation(@Valid @RequestBody AgentReservationRequest reservationRequest,
                                                     HttpServletRequest request) {
        reservationRequest.setEmail(CurrentUser.getEmail(request));
        return reservationService.makeAgentReservation(reservationRequest);
    }

    @PostMapping("/reservations/confirm")
    public ReservationResponse confirmReservation(@Valid @RequestBody ConfirmReservationRequest reservationRequest,
                                                  HttpServletRequest request)  {
        reservationRequest.setEmail(CurrentUser.getEmail(request));
        return reservationService.agentConfirmReservation(reservationRequest);
    }

    @PostMapping("/reservations/cancel")
    public ReservationResponse cancelReservation(@Valid @RequestBody CancelReservationRequest reservationRequest,
                                                 HttpServletRequest request)  {
        reservationRequest.setEmail(CurrentUser.getEmail(request));
        return reservationService.agentCancelReservationByCode(reservationRequest);
    }
}
