package pl.pjatk.mas.project.control.entity;

import lombok.*;
import pl.pjatk.mas.project.control.entity.enums.TripType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TripEntity extends AuditingEntity {
    @Id
    @Column(name = "TRIP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRIP_SQ")
    @SequenceGenerator(name = "TRIP_SQ", sequenceName = "TRIPS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "DATE_TIME", nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private TripType type;

    @Column(name = "PLACE_COUNT", nullable = false)
    private Integer placeCount;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @OneToMany(
            targetEntity = EmployeeTripEntity.class,
            mappedBy = "trip",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<EmployeeTripEntity> employees = new HashSet<>();

    @OneToMany(
            targetEntity = StopEntity.class,
            mappedBy = "trip",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<StopEntity> stops = new HashSet<>();

    @OneToMany(
            targetEntity = TicketEntity.class,
            mappedBy = "trip",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<TicketEntity> tickets = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, targetEntity = TrainEntity.class)
    TrainEntity train;

    public void addStop(StopEntity stopEntity) {
        stopEntity.setTrip(this);
        stops.add(stopEntity);
    }

    public void addEmployee(EmployeeEntity employee, Integer salary) {
        EmployeeTripEntity employeeTripEntity = EmployeeTripEntity.builder().trip(this).employee(employee).salary(salary).build();
        employees.add(employeeTripEntity);
        employee.getTrips().add(employeeTripEntity);
    }

    public void removeEmployee(EmployeeEntity employee) {
        for (Iterator<EmployeeTripEntity> iterator = employees.iterator();
             iterator.hasNext(); ) {
            EmployeeTripEntity employeeTrip = iterator.next();

            if (employeeTrip.getTrip().equals(this) &&
                    employeeTrip.getEmployee().equals(employee)) {
                iterator.remove();
                employeeTrip.getEmployee().getTrips().remove(employeeTrip);
                employeeTrip.setEmployee(null);
                employeeTrip.setTrip(null);
            }
        }
    }

    @Builder
    public TripEntity(String name, LocalDateTime dateTime, TripType type, Integer placeCount, Double price, TrainEntity train) {
        this.name = name;
        this.dateTime = dateTime;
        this.type = type;
        this.placeCount = placeCount;
        this.price = price;
        this.train = train;
    }
}