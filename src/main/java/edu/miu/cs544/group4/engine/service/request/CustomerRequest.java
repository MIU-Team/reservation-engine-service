package edu.miu.cs544.group4.engine.service.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class CustomerRequest {
    @NotBlank(message = "Customer name is mandatory")
    private String name;
    private String email;
    private String phoneNumber;
}
