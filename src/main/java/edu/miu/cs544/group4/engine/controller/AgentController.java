package edu.miu.cs544.group4.engine.controller;

import edu.miu.cs544.group4.engine.service.ReservationService;
import edu.miu.cs544.group4.engine.service.request.CancelReservationRequest;
import edu.miu.cs544.group4.engine.service.request.ConfirmReservationRequest;
import edu.miu.cs544.group4.engine.service.request.ReservationRequest;
import edu.miu.cs544.group4.engine.service.response.PassengerReservationResponse;
        import edu.miu.cs544.group4.engine.service.response.ReservationResponse;
import edu.miu.cs544.group4.engine.service.response.ReservationResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author knguyen93
 */
@RestController
@RequestMapping("/agent")
public class AgentController extends BaseReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations/{email}")
    public List<ReservationResponse> getAllReservations(@PathVariable String email) {
        return reservationService.getAgentReservationsByEmail(email);
    }

    @GetMapping("/passenger-reservations/{email}")
    public List<PassengerReservationResponse> getAllCustomerPassengersAndTheirReservations(@PathVariable String email) {
        return reservationService.getAllCustomerPassengersAndTheirReservations(email);
    }

    @PutMapping("/reservations")
    public ReservationResultResponse makeReservation(@RequestBody ReservationRequest request) {
        return reservationService.makeAgentReservation(request);
    }

    @PostMapping("/reservations/confirm")
    public ReservationResponse confirmReservation(@RequestBody ConfirmReservationRequest request)  {
        return reservationService.agentConfirmReservation(request);
    }

    @PostMapping("/reservations/cancel")
    public ReservationResponse cancelReservation(@RequestBody CancelReservationRequest request)  {
        return reservationService.agentCancelReservationByCode(request);
    }
}
