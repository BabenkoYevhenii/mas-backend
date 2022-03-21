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
public class TrainEntity  extends AuditingEntity {
    @Id
    @Column(name = "TRAIN_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAIN_SQ")
    @SequenceGenerator(name = "TRAIN_SQ", sequenceName = "TRAINS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "SERIAL_NUMBER", unique = true)
    private String serialNumber;

    @Column(name = "POWER", nullable = false)
    private String power;

    @Column(name = "MAX_CARRIAGE_COUNT")
    private Integer maxCarriageCount;

    @Column(name = "MANUFACTURER", nullable = false)
    private String manufacturer;

    @OneToOne(mappedBy = "train", targetEntity = TripEntity.class)
    @ToString.Exclude
    private TripEntity trip;

    @OneToMany(targetEntity = CarriageEntity.class, mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CarriageEntity> carriages = new HashSet<>();

    public void addCarriage(CarriageEntity carriageEntity) {
        carriageEntity.setTrain(this);
        carriages.add(carriageEntity);
    }

    @Builder
    public TrainEntity(String serialNumber, String power, Integer maxCarriageCount, String manufacturer, TripEntity trip) {
        this.serialNumber = serialNumber;
        this.power = power;
        this.maxCarriageCount = maxCarriageCount;
        this.manufacturer = manufacturer;
        this.trip = trip;
    }
}