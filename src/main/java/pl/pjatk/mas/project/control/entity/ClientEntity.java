package pl.pjatk.mas.project.control.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientEntity {
    @Id
    @Column(name = "CLIENT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_SQ")
    @SequenceGenerator(name = "CLIENT_SQ", sequenceName = "CLIENTS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @ToString.Exclude
    private UserEntity user;

    @OneToMany(targetEntity = PassengerEntity.class, mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<PassengerEntity> attenders = new HashSet<>();

    @OneToMany(targetEntity = PromotionEntity.class, mappedBy = "client",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<PromotionEntity> promotions = new HashSet<>();

    @OneToMany(
            targetEntity = OrderEntity.class,
            mappedBy = "client",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderEntity> orders = new HashSet<>();

    public void addPromotion(PromotionEntity promotion) {
        promotions.add(promotion);
        promotion.setClient(this);
    }

    @Builder
    public ClientEntity(String phone, String address, UserEntity user) {
        this.phone = phone;
        this.address = address;
        this.user = user;
    }
}
