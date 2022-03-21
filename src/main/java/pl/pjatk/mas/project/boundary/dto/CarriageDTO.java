
package pl.pjatk.mas.project.boundary.dto;

        import lombok.*;
        import pl.pjatk.mas.project.control.entity.enums.CarriageType;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor

public class CarriageDTO {
    private Long id;
    private String serialNumber;
    private String numberOfSeats;
    private String manufacturer;
    private CarriageType type;

    @Builder
    public CarriageDTO(Long id, String serialNumber, String numberOfSeats, String manufacturer, CarriageType type) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.numberOfSeats = numberOfSeats;
        this.manufacturer = manufacturer;
    }
}
