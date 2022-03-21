package pl.pjatk.mas.project.boundary.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor

public class TrainDTO {
    private Long id;
    private String serialNumber;
    private String power;
    private String manufacturer;
    private List<CarriageDTO> carriages;

    @Builder
    public TrainDTO(Long id, String serialNumber, String power, String manufacturer) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.power = power;
        this.manufacturer = manufacturer;
    }
}
