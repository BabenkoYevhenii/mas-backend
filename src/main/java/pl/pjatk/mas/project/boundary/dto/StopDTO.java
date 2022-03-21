
package pl.pjatk.mas.project.boundary.dto;

        import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor

public class StopDTO {
    private Long id;
    private String name;
    private String city;
    private String street;
    private String buildingNumber;

    @Builder
    public StopDTO(Long id, String name, String city, String street,String buildingNumber) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }
}
