package edu.miu.cs544.group4.engine.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 3, min = 3, message = "Airport code should be 3 charachters")
	@NotBlank(message = "Airport code is mandatory")
	private String code;
	@NotBlank(message = "Airport name is mandatory")
    private String name;
    private AddressResponse address;

}
