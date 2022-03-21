package pl.pjatk.mas.project.control.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeTripEntity extends AuditingEntity {
    @EmbeddedId
    EmployeeTripKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("EMPLOYEE_ID")
    @JoinColumn(name = "EMPLOYEE_ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("TRIP_ID")
    @JoinColumn(name = "TRIP_ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TripEntity trip;

    private Integer salary;

    @PrePersist
    private void prePersist() {
        if (getId() == null) {
            EmployeeTripKey pk = new EmployeeTripKey();
            pk.setEmployeeId(getEmployee().getId());
            pk.setTripId(getTrip().getId());
            setId(pk);
        }
    }

    @Builder
    public EmployeeTripEntity(EmployeeEntity employee, TripEntity trip, Integer salary) {
        this.employee = employee;
        this.trip = trip;
        this.salary = salary;
    }
}
