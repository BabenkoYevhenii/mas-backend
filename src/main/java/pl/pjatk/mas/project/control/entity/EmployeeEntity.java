package pl.pjatk.mas.project.control.entity;

import lombok.*;
import pl.pjatk.mas.project.control.entity.enums.EmployeeType;

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
public class EmployeeEntity extends AuditingEntity {
    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SQ")
    @SequenceGenerator(name = "EMPLOYEE_SQ", sequenceName = "EMPLOYEES_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private EmployeeType type;

    @OneToMany(mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<EmployeeTripEntity> trips = new HashSet<>();

    @Builder
    public EmployeeEntity(String name, EmployeeType type) {
        this.name = name;
        this.type = type;
    }
}
