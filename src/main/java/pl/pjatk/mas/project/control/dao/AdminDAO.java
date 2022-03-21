package pl.pjatk.mas.project.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.mas.project.control.entity.AdminEntity;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AdminDAO
        extends JpaRepository<AdminEntity, Long> {
}
