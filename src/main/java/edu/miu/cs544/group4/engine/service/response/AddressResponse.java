package edu.miu.cs544.group4.engine.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String street;
    private String city;
    private Integer zip;
    private String state;
}
