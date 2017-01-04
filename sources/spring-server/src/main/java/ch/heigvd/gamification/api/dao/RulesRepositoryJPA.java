package ch.heigvd.gamification.api.dao;

import ch.heigvd.gamification.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Antony
 */
public interface RulesRepositoryJPA extends JpaRepository<Rule, Long>{
    
}
