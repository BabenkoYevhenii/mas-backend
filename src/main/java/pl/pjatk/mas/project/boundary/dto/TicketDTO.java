package pl.pjatk.mas.project.boundary.dto;

import lombok.*;
import pl.pjatk.mas.project.control.entity.PassengerEntity;
import pl.pjatk.mas.project.control.entity.enums.TicketType;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private Double price;
    private String placeNumber;
    private TicketType type;
    private PassengerDTO passenger;
    private String tripName;
    private LocalDateTime tripDate;
    private Long tripId;
}
