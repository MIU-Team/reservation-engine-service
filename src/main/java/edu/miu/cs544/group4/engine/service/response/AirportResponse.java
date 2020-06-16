package edu.miu.cs544.group4.engine.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private AddressResponse address;

}
