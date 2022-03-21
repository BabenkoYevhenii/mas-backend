package pl.pjatk.mas.project.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.mas.project.control.entity.PromotionEntity;

public interface PromotionDAO extends JpaRepository<PromotionEntity, Long> {
}
