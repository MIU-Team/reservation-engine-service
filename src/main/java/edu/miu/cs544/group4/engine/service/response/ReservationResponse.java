package edu.miu.cs544.group4.engine.service.response;

import edu.miu.cs544.group4.engine.model.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private ReservationStatus status;
    private Date reservationTime;
    private List<TicketResponse> tickets;
    private CustomerResponse customer;
    private CustomerResponse agent;
}
