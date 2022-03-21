package pl.pjatk.mas.project.control.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import pl.pjatk.mas.project.control.entity.enums.PromotionType;
import pl.pjatk.mas.project.control.entity.enums.Role;
import pl.pjatk.mas.project.control.entity.enums.TicketType;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TicketEntity {
    @Id
    @Column(name = "TICKET_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TICKET_SQ")
    @SequenceGenerator(name = "TICKET_SQ", sequenceName = "TICKETS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "PLACE", nullable = false)
    private String placeNumber;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "TICKET_TYPE")
    @Enumerated(EnumType.STRING)
    private TicketType type;

    @ManyToOne(targetEntity = TripEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TRIP_ID", referencedColumnName = "TRIP_ID")
    @EqualsAndHashCode.Exclude
    private TripEntity trip;

    @ManyToOne(targetEntity = PassengerEntity.class)
    @JoinColumn(name = "PASSENGER_ID", referencedColumnName = "PASSENGER_ID")
    @EqualsAndHashCode.Exclude
    private PassengerEntity passenger;

    @ManyToOne(targetEntity = OrderEntity.class)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OrderEntity order;

    public void addTrip(TripEntity trip) {
        setPlaceNumber(trip.getTickets().size() + 1 + "A");
        setTrip(trip);
        trip.getTickets().add(this);
    }

    @Builder
    public TicketEntity(String placeNumber, Double price, TicketType type, TripEntity trip, PassengerEntity passenger, OrderEntity order) {
        this.placeNumber = placeNumber;
        this.price = price;
        this.type = type;
        this.trip = trip;
        this.passenger = passenger;
        this.order = order;
    }
}