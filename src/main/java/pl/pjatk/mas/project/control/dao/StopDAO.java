package pl.pjatk.mas.project.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.mas.project.control.entity.StopEntity;

public interface StopDAO extends JpaRepository<StopEntity, Long> {
}
