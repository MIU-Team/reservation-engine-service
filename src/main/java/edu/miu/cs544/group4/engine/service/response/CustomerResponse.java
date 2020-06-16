package edu.miu.cs544.group4.engine.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String phoneNumber;
    private String email;
    private String role;

}

