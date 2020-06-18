package edu.miu.cs544.group4.engine.schedule;

import edu.miu.cs544.group4.engine.service.ReservationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * @author knguyen93
 */
@Log4j2
@Component
public class FlightReminder {
    private RabbitTemplate rabbitTemplate;
    private ReservationService reservationService;
    private static final String MSG = "This is the Flight Reminder. Your reservation has flight(s) will departure " +
        "soon within next 24 hours. \\nPlease check the Reservation with code %s to view the Flight details.";

    @Autowired
    public FlightReminder(RabbitTemplate rabbitTemplate, ReservationService reservationService) {
        this.rabbitTemplate = rabbitTemplate;
        this.reservationService = reservationService;
        this.rabbitTemplate.setExchange("reservation-service-exchange");
    }

    @Scheduled(cron = "0/10 * * * * *") //every 10 seconds
    //@Scheduled(cron = "* * 12 * * *") //every 12 hours
    public void checkFlightReminder() {
        Map<String, String> data = new HashMap<>();
        data.put("subject", "Flight reminder");

        reservationService.get24HoursPriorDepartureReservations()
            .stream()
            .forEach(r -> {
                data.put("toAddress", r.getCustomerEmail());
                data.put("message", String.format(MSG, r.getCode()));
                log.info("Sending message to JMS...");
                rabbitTemplate.convertAndSend("flight.notifi.email", data);
            });
    }

}
