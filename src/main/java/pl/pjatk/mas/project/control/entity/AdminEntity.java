package pl.pjatk.mas.project.control.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdminEntity {
    @Id
    @Column(name = "ADMIN_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_SQ")
    @SequenceGenerator(name = "ADMIN_SQ", sequenceName = "ADMINS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "IDENTIFIER", nullable = false, unique = true)
    private String identifier;

    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @ToString.Exclude
    private UserEntity user;

    @Builder
    public AdminEntity(String identifier, UserEntity user) {
        this.identifier = identifier;
        this.user = user;
    }
}
