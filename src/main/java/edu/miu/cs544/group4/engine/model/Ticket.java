package edu.miu.cs544.group4.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 20)
    private String ticketNumber;
    @Temporal(TemporalType.DATE)
    private LocalDate flightDate;
    @ManyToOne
    @JoinTable(name = "ticket_reservation")
    private Reservation reservation;
    @OneToOne
    private Passenger passenger;
}
