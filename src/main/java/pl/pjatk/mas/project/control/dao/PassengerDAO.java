package pl.pjatk.mas.project.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.mas.project.control.entity.PassengerEntity;

import java.util.List;

public interface PassengerDAO extends JpaRepository<PassengerEntity, Long> {
    List<PassengerEntity> findAllByClient_Id(Long clientId);
}
