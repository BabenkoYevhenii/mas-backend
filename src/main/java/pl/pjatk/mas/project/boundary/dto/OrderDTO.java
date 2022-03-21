package pl.pjatk.mas.project.boundary.dto;

import lombok.*;
import pl.pjatk.mas.project.control.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private List<TicketDTO> tickets;
    private ClientDTO client;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private Integer totalPrice;

    @Builder
    public OrderDTO(Long id, List<TicketDTO> tickets, ClientDTO client, OrderStatus status, LocalDateTime createdAt, Integer totalPrice) {
        this.id = id;
        this.tickets = tickets;
        this.client = client;
        this.status = status;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
    }
}
