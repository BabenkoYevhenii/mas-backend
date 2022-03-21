package pl.pjatk.mas.project.control.service;

import pl.pjatk.mas.project.boundary.dto.OrderDTO;
import pl.pjatk.mas.project.boundary.dto.PassengerDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllClientOrders(Long clientId);

    List<PassengerDTO> getAllClientPassengers(Long clientId);

    PassengerDTO createPassenger(Long clientId, PassengerDTO passengerDto);

    OrderDTO createOrder(Long clientId, Long tripId, OrderDTO orderDto);

    OrderDTO payForOrder(Long clientId, Long orderId, Integer amount);
}
