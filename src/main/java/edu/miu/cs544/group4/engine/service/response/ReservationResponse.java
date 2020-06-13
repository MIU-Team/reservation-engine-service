package edu.miu.cs544.group4.engine.service.response;

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
    private Integer id;
    private String code;
    private Date reservationTime;
    private List<TicketResponse> tickets;
    private CustomerResponse customer;
}
