package pl.pjatk.mas.project.boundary.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO extends UserDTO {
    private String identifier;

    @Builder
    public AdminDTO(Long id, String name, String surname, String email, String accessToken, String adminIdentifier) {
        super(id, name, surname, email, accessToken);
        this.identifier = adminIdentifier;
    }
}
