package ch.heigvd.gamification.api.dao;

import ch.heigvd.gamification.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Antony
 */
public interface BadgesRepositoryJPA extends JpaRepository<Badge, Long>{

    
}
