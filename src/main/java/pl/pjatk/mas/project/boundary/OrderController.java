package pl.pjatk.mas.project.boundary;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.mas.project.boundary.dto.OrderDTO;
import pl.pjatk.mas.project.boundary.dto.PassengerDTO;
import pl.pjatk.mas.project.control.security.CurrentUser;
import pl.pjatk.mas.project.control.security.UserPrincipal;
import pl.pjatk.mas.project.control.service.OrderService;

import java.util.List;

@RestController
@RequestMapping({"/api/orders"})
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    @NonNull OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@CurrentUser UserPrincipal client, @RequestParam Long eventId, @RequestBody OrderDTO orderDto) {
        return ResponseEntity.ok(orderService.createOrder(client.getId(), eventId, orderDto));
    }

    @PutMapping("/pay/{orderId}")
    public ResponseEntity<OrderDTO> payForOrder(@CurrentUser UserPrincipal client, @PathVariable Long orderId, @RequestParam Integer amount) {
        return ResponseEntity.ok(orderService.payForOrder(client.getId(), orderId, amount));
    }

    @GetMapping("/client")
    public ResponseEntity<List<OrderDTO>> getAllClientOrders(@CurrentUser UserPrincipal client) {
        return ResponseEntity.ok(orderService.getAllClientOrders(client.getId()));
    }

    @GetMapping("/attenders")
    public ResponseEntity<List<PassengerDTO>> getAllClientPassengers(@CurrentUser UserPrincipal client) {
        return ResponseEntity.ok(orderService.getAllClientPassengers(client.getId()));
    }

    @PostMapping("/attenders")
    public ResponseEntity<PassengerDTO> createPassenger(@CurrentUser UserPrincipal client, @RequestBody PassengerDTO passengerDto) {
        return ResponseEntity.ok(orderService.createPassenger(client.getId(), passengerDto));
    }
}
