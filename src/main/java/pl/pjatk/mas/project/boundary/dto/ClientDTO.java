package pl.pjatk.mas.project.boundary.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO extends UserDTO {
    private String phone;
    private String address;

    @Builder
    public ClientDTO(Long id, String name, String surname, String email, String accessToken, String phone, String address) {
        super(id, name, surname, email, accessToken);
        this.phone = phone;
        this.address = address;
    }
}
