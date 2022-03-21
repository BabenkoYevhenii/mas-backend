package pl.pjatk.mas.project.boundary.dto;

import lombok.*;


import pl.pjatk.mas.project.control.entity.enums.DocumentType;

        import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class PassengerDTO {
    private Long id;
    private Integer age;
    private LocalDate birthdate;
    private String name;
    private String surname;
    private DocumentType documentType;
    private String documentNumber;

    @Builder
    public PassengerDTO(Long id, Integer age, LocalDate birthdate, String name, String surname, DocumentType documentType, String documentNumber) {
        this.id = id;
        this.age = age;
        this.birthdate = birthdate;
        this.name = name;
        this.surname = surname;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }
}
