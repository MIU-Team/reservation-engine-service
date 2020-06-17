package edu.miu.cs544.group4.engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
    {
        @NamedQuery(
            name = "Reservation.getAllReservationByCustomerEmail",
            query = "SELECT r FROM Reservation r WHERE r.customer.email = :email"
        ),
        @NamedQuery(
            name = "Reservation.getAllReservationsMatchTheDepartureTime",
            query = "SELECT DISTINCT r FROM Reservation r JOIN r.flights f " +
                "WHERE r.status = 'CONFIRMED' AND f.departureTime <= :departureTime"
        )
    }
)
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date reservationTime;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Customer agent;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "reservation_flight",
        joinColumns = @JoinColumn(name = "reservation_code", referencedColumnName = "code"),
        inverseJoinColumns = @JoinColumn(name = "flight_number", referencedColumnName = "flightNumber"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"reservation_code", "flight_number" })
    )
    private Set<Flight> flights;


    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.NEW;

    public boolean canCancel() {
        return ReservationStatus.NEW.equals(status)
            || ReservationStatus.CONFIRMED.equals(status);
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELED;
    }

    public boolean canConfirm() {
        return ReservationStatus.NEW.equals(status);
    }

    public void confirm() {
        this.status = ReservationStatus.CONFIRMED;
    }

    public void addTickets(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticket.setReservation(this);
        }

        this.tickets.addAll(tickets);
    }

}
