
package ch.heigvd.gamification.api.dao;

import ch.heigvd.gamification.model.PointScale;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Antony
 */
public interface PointScalesRepositoryJPA extends JpaRepository<PointScale, Long>{
    
    public PointScale findByName(String pointScaleName);
    
}
