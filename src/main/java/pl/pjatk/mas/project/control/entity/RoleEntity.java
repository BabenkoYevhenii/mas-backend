package pl.pjatk.mas.project.control.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import pl.pjatk.mas.project.control.entity.enums.Role;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoleEntity {
    @Id
    @Column(name = "ROLE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SQ")
    @SequenceGenerator(name = "ROLE_SQ", sequenceName = "ROLES_SEQ", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private Role name;

    @Builder
    public RoleEntity(Role name) {
        this.name = name;
    }
}