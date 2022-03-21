package pl.pjatk.mas.project.control.entity;

import lombok.*;
import pl.pjatk.mas.project.control.entity.enums.CarriageType;
import pl.pjatk.mas.project.control.entity.enums.EmployeeType;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CarriageEntity extends AuditingEntity {
    @Id
    @Column(name = "CARRIAGE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARRIAGE_SQ")
    @SequenceGenerator(name = "CARRIAGE_SQ", sequenceName = "CARRIAGES_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "SERIAL_NUMBER", unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private CarriageType type;

    @Column(name = "NUMBER_OF_SEATS")
    private Integer numberOfSeats;

    @Column(name = "MANUFACTURER", nullable = false)
    private String manufacturer;

    @ManyToOne(targetEntity = TrainEntity.class)
    @JoinColumn(name = "TRAIN_ID", referencedColumnName = "TRAIN_ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TrainEntity train;

    @Builder
    public CarriageEntity(String serialNumber, CarriageType type, Integer numberOfSeats, String manufacturer, TrainEntity train) {
        this.serialNumber = serialNumber;
        this.type = type;
        this.numberOfSeats = numberOfSeats;
        this.manufacturer = manufacturer;
        this.train = train;
    }
}
