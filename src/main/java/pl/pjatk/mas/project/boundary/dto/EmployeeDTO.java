package pl.pjatk.mas.project.boundary.dto;

import lombok.*;
import pl.pjatk.mas.project.control.entity.enums.EmployeeType;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private Integer salary;
    private EmployeeType type;

    @Builder
    public EmployeeDTO(Long id, String name, String style, EmployeeType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
