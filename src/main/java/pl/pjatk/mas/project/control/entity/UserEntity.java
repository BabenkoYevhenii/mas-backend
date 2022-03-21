package pl.pjatk.mas.project.control.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity extends PersonEntity {
    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SQ")
    @SequenceGenerator(name = "USER_SQ", sequenceName = "USERS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = ClientEntity.class)
    ClientEntity client;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = AdminEntity.class)
    AdminEntity admin;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleEntity> roles = new HashSet<>();

    public void addClient(ClientEntity clientEntity) {
        this.setClient(clientEntity);
        clientEntity.setUser(this);
    }

    public void addAdmin(AdminEntity adminEntity) {
        this.setAdmin(adminEntity);
        adminEntity.setUser(this);
    }

    @Builder
    public UserEntity(String name, String surname, String email, String password, ClientEntity client, AdminEntity admin) {
        super(name, surname);
        this.email = email;
        this.password = password;
        this.client = client;
        this.admin = admin;
    }
}
