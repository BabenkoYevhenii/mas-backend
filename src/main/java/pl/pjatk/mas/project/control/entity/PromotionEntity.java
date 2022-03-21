package pl.pjatk.mas.project.control.entity;

import lombok.*;
import pl.pjatk.mas.project.control.entity.enums.PromotionType;
import pl.pjatk.mas.project.control.entity.enums.TripType;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PromotionEntity extends AuditingEntity {
    @Id
    @Column(name = "PROMOTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROMOTION_SQ")
    @SequenceGenerator(name = "PROMOTION_SQ", sequenceName = "PROMOTIONS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "DISCOUNT_PERCENT")
    private Integer discountPercent;

    @Column(name = "PROMOTION_TYPE")
    @Enumerated(EnumType.STRING)
    private PromotionType type;

    @ManyToOne(targetEntity = ClientEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ClientEntity client;

    @Builder
    public PromotionEntity(Integer discountPercent, PromotionType type, ClientEntity client) {
        this.discountPercent = discountPercent;
        this.type = type;
        this.client = client;
    }
}