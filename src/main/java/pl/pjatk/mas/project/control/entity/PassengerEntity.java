package pl.pjatk.mas.project.control.entity;

import lombok.*;
import pl.pjatk.mas.project.control.entity.enums.DocumentType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PassengerEntity extends PersonEntity {
    @Id
    @Column(name = "PASSENGER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PASSENGER_SQ")
    @SequenceGenerator(name = "PASSENGER_SQ", sequenceName = "PASSENGERS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;

    public Integer getAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    @Column(name = "DOCUMENT_NUMBER", nullable = false)
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "DOCUMENT_TYPE", nullable = false)
    private DocumentType documentType;

    @OneToMany(
            targetEntity = TicketEntity.class,
            mappedBy = "passenger",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<TicketEntity> tickets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ClientEntity client;

    @Builder
    public PassengerEntity(String name, String surname, LocalDate birthdate, String documentNumber, DocumentType documentType, ClientEntity client) {
        super(name, surname);
        this.birthdate = birthdate;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.client = client;
    }
}