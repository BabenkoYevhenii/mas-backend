package pl.pjatk.mas.project.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.mas.project.control.entity.TripEntity;

public interface TripDAO extends JpaRepository<TripEntity, Long> {
}
