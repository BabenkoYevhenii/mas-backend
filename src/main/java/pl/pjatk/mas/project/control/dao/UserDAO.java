package pl.pjatk.mas.project.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.mas.project.control.entity.UserEntity;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserDAO
        extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
