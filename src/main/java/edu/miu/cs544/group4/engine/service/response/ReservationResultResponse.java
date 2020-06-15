package edu.miu.cs544.group4.engine.service.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ReservationResultResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String code;
    private Date reservationTime;
    private List<FlightResponse> flights;
    private List<String> invalidFlightNumbers;
}
