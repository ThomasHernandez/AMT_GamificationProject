package ch.heigvd.gamification.api.dao;

import ch.heigvd.gamification.model.CurrentPoints;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Antony
 */
public interface CurrentPointsRepositoryJPA extends JpaRepository<CurrentPoints, Long>{
    
}
