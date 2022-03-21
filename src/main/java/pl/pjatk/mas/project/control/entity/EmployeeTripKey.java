package pl.pjatk.mas.project.control.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
class EmployeeTripKey implements Serializable {
    @Column(name = "EMPLOYEE_ID")
    Long employeeId;

    @Column(name = "TRIP_ID")
    Long tripId;

    @Builder
    public EmployeeTripKey(Long employeeId, Long tripId) {
        this.employeeId = employeeId;
        this.tripId = tripId;
    }
}