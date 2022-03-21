package pl.pjatk.mas.project.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.mas.project.control.entity.ClientEntity;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ClientDAO
        extends JpaRepository<ClientEntity, Long> {
}
