package edu.miu.cs544.group4.engine.controller;

import edu.miu.cs544.group4.engine.service.ReservationService;
        import edu.miu.cs544.group4.engine.service.response.PassengerReservationResponse;
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
@RequestMapping("/agent")
public class AgentController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations/{email}")
    public List<ReservationResponse> getAllReservations(@PathVariable String email) {
        return reservationService.getAllReservationsByEmail(email);
    }

    @GetMapping("/passenger-reservations/{email}")
    public List<PassengerReservationResponse> getAllCustomerPassengersAndTheirReservations(@PathVariable String email) {
        return reservationService.getAllCustomerPassengersAndTheirReservations(email);
    }
}
