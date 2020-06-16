package edu.miu.cs544.group4.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
    name="Airline.findAirlinesOutOfAnAirport",
    query="SELECT distinct  al FROM Flight f JOIN f.airline al WHERE f.origin.code=:airportCode"
)
public class Airline implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 2)
    private String code;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private History history;
    @Embedded
    private Address address;
}
