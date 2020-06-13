package edu.miu.cs544.group4.engine.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirlineResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String code;
    private String name;
    private HistoryResponse history;
    private AddressResponse address;
}
