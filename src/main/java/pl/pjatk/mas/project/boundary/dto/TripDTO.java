package pl.pjatk.mas.project.boundary.dto;

import lombok.*;
import pl.pjatk.mas.project.control.entity.enums.TripType;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class TripDTO {
    private Long id;
    private LocalDateTime dateTime;
    private String name;
    private TripType type;
    private Double price;
    private TrainDTO train;
    private List<StopDTO> stops;
    private List<EmployeeDTO> employees;

    @Builder
    public TripDTO(Long id, LocalDateTime dateTime, String name, TripType type, Double price, TrainDTO train, List<StopDTO> stops, List<EmployeeDTO> employees) {
        this.id = id;
        this.dateTime = dateTime;
        this.name = name;
        this.type = type;
        this.price = price;
        this.train = train;
        this.stops = stops;
        this.employees = employees;
    }
}
