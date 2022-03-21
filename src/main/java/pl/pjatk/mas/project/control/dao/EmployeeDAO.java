package pl.pjatk.mas.project.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.mas.project.control.entity.EmployeeEntity;

public interface EmployeeDAO extends JpaRepository<EmployeeEntity, Long> {
}
