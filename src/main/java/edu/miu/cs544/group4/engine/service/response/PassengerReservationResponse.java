package edu.miu.cs544.group4.engine.service.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class PassengerReservationResponse {
    private String firstName;
    private String lastName;
    private String email;
    private AddressResponse address;
    private List<String> reservationCodes = new ArrayList<>();

}
