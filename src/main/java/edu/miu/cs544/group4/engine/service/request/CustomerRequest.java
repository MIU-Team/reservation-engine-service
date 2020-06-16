package edu.miu.cs544.group4.engine.service.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerRequest {
    private String name;
    private String email;
    private String phoneNumber;
}
