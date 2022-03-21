package pl.pjatk.mas.project.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.mas.project.control.entity.OrderEntity;
import pl.pjatk.mas.project.control.entity.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDAO extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByClient_Id(Long id);
    Optional<OrderEntity> findByIdAndStatusAndClient_Id(Long id, OrderStatus orderStatus, Long clientId);
}
