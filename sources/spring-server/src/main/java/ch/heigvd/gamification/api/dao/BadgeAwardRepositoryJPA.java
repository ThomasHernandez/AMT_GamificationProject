package ch.heigvd.gamification.api.dao;

import ch.heigvd.gamification.model.BadgeAward;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Antony
 */
public interface BadgeAwardRepositoryJPA extends JpaRepository<BadgeAward, Long>{
    
}
