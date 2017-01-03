package ch.heigvd.gamification.api.dao;

import ch.heigvd.gamification.model.GamifiedApplication;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Antony
 */
public interface GamifiedApplicationRepositoryJPA extends JpaRepository<GamifiedApplication, Long>{
    
    public GamifiedApplication findByName(String appName);
    public GamifiedApplication findByAuthToken(String authToken);
    
}
