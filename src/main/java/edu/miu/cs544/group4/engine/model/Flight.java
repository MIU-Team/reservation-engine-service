package edu.miu.cs544.group4.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@NamedQueries(
    {
        @NamedQuery(
        name = "Flight.getFlightsOnRouteAndDate",
        query = "SELECT f from Flight f " +
            "WHERE f.origin.code = :originCode AND f.destination.code = :destinationCode " +
            "AND f.departureTime BETWEEN :fromDate AND :toDate"
        ),
        @NamedQuery(
            name = "Flight.getFlightsOnRoute",
            query = "SELECT f from Flight f " +
                "WHERE f.origin.code = :originCode AND f.destination.code = :destinationCode "
        )
    }
)
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer capacity;
    private Integer availableSeats;
    @Column(length = 4)
    private String flightNumber;
    @Column(columnDefinition = "TIMESTAMP")
    private Date departureTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;
    @ManyToOne
    @JoinColumn(name = "origin_airport_id")
    private Airport origin;
    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;

    public boolean canBookFlight(Integer seatsToBook) {
        return this.availableSeats >= seatsToBook;
    }
    public void updateAvailableSeats(Integer seatsBooked) {
        this.availableSeats -= seatsBooked;
    }
}
