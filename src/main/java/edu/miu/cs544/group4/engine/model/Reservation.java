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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
    {
        @NamedQuery(
            name = "Reservation.getAllReservationByCustomerEmail",
            query = "SELECT r FROM Reservation r WHERE r.customer.email = :email"
        )
    }
)
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
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

    @OneToMany
    @JoinTable(
        name = "reservation_flight",
        joinColumns = @JoinColumn(name = "reservation_id"),
        inverseJoinColumns = @JoinColumn(name = "flight_id"))
    private List<Flight> flights;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.NEW;

    public boolean canCancel() {
        return ReservationStatus.NEW.equals(status)
            || ReservationStatus.CONFIRMED.equals(status);
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELED;
    }

    public void addTickets(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticket.setReservation(this);
        }

        this.tickets.addAll(tickets);
    }

}
