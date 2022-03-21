package pl.pjatk.mas.project.control.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.pjatk.mas.project.boundary.dto.OrderDTO;
import pl.pjatk.mas.project.boundary.dto.PassengerDTO;
import pl.pjatk.mas.project.control.dao.*;
import pl.pjatk.mas.project.control.entity.*;
import pl.pjatk.mas.project.control.entity.enums.OrderStatus;
import pl.pjatk.mas.project.control.mapper.ProjectMapper;
import pl.pjatk.mas.project.control.service.OrderService;
import pl.pjatk.mas.project.controller.exceptions.EntityNotFoundException;
import pl.pjatk.mas.project.controller.exceptions.SomeException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @NonNull UserDAO userDao;
    @NonNull ClientDAO clientDao;
    @NonNull TripDAO tripDao;
    @NonNull OrderDAO orderDao;
    @NonNull PassengerDAO passengerDao;
    @NonNull ProjectMapper mapper;

    @Override
    public List<OrderDTO> getAllClientOrders(Long userId) {
        UserEntity user = userDao.findById(userId).orElseThrow(EntityNotFoundException::new);
        log.info("User data: {}", user);
        return orderDao.findAllByClient_Id(user.getClient().getId()).stream()
                .map(mapper::orderEntityToDto)
                .sorted(Comparator.comparing(OrderDTO::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<PassengerDTO> getAllClientPassengers(Long userId) {
        UserEntity user = userDao.findById(userId).orElseThrow(EntityNotFoundException::new);
        log.info("User data: {}", user);
        return passengerDao.findAllByClient_Id(user.getClient().getId()).stream()
                .map(mapper::passengerEntityToDto)
                .sorted(Comparator.comparing(PassengerDTO::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public PassengerDTO createPassenger(Long clientId, PassengerDTO attenderDto) {
        UserEntity user = userDao.findById(clientId).orElseThrow(EntityNotFoundException::new);
        log.info("User data: {}", user);
        PassengerEntity attenderEntity = mapper.passengerDtoToEntity(attenderDto);
        attenderEntity.setClient(user.getClient());
        return mapper.passengerEntityToDto(passengerDao.save(attenderEntity));
    }

    @Override
    public OrderDTO createOrder(Long clientId, Long tripId, OrderDTO orderDto) {
        UserEntity user = userDao.findById(clientId).orElseThrow(EntityNotFoundException::new);
        log.info("User data: {}", user);
        TripEntity trip = tripDao.findById(tripId).orElseThrow(EntityNotFoundException::new);
        OrderEntity order = OrderEntity.builder()
                .totalPrice(orderDto.getTotalPrice())
                .status(OrderStatus.CONFIRMED)
                .client(user.getClient())
                .build();

        orderDto.getTickets().forEach(ticketDto -> {
            TicketEntity ticketEntity = mapper.ticketDtoToEntity(ticketDto);
            ticketEntity.addTrip(trip);
            order.addTicket(ticketEntity);
        });

        log.info("Order data: {}", order);

        return mapper.orderEntityToDto(orderDao.save(order));
    }

    @Override
    public OrderDTO payForOrder(Long userId, Long orderId, Integer amount) {
        UserEntity user = userDao.findById(userId).orElseThrow(EntityNotFoundException::new);
        log.info("User data: {}", user);
        OrderEntity order = orderDao.findByIdAndStatusAndClient_Id(orderId, OrderStatus.CONFIRMED, user.getClient().getId()).orElseThrow(EntityNotFoundException::new);
        if (amount.equals(order.getTotalPrice())) {
            order.setStatus(OrderStatus.PAID);
            log.info("Paid for order: {}", orderId);
            return mapper.orderEntityToDto(orderDao.save(order));
        } else throw new SomeException("amount: " + amount + " is dif then totalPrice: " + order.getTotalPrice());
    }
}
