package pl.pjatk.mas.project.control.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StopEntity  extends AuditingEntity {
    @Id
    @Column(name = "STOP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STOP_SQ")
    @SequenceGenerator(name = "STOP_SQ", sequenceName = "STOPS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "BUILDING_NUMBER", nullable = false)
    private String buildingNumber;

    @ManyToOne(targetEntity = TripEntity.class)
    @JoinColumn(name = "TRIP_ID", referencedColumnName = "TRIP_ID")
    @EqualsAndHashCode.Exclude
    private TripEntity trip;

    @Builder
    public StopEntity(String name, String city, String street, String buildingNumber, TripEntity trip) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.trip = trip;
    }
}