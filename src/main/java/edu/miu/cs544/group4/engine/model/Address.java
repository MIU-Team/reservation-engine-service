package edu.miu.cs544.group4.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  private String street;
  private String city;
  private String zip;
  private String state;
}
