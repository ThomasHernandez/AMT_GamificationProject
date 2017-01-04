package ch.heigvd.gamification.services;

import ch.heigvd.gamification.api.dao.ApplicationUserRepositoryJPA;
import ch.heigvd.gamification.model.ApplicationUser;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.GameEvent;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.model.PointScale;
import ch.heigvd.gamification.model.Rule;
import java.util.LinkedList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Antony
 */
@Service
public class EventProcessor {
    
    @Autowired
    private  ApplicationUserRepositoryJPA usersRepository;
        
    @Async
    @Transactional
    public void processEvent(GamifiedApplication app, GameEvent e){
        
        ApplicationUser targetUser = usersRepository.findByApplicationNameAndIdInGamifiedApplication(app.getName(), e.getAppUserId());
        
        // Registering new user
        if(targetUser == null){
            
            targetUser = new ApplicationUser();
            targetUser.setApplication(app);
            targetUser.setIdInGamifiedApplication(e.getAppUserId());
            targetUser.setNbEvents(0);
            
            List<PointScale> newUserPoints = new LinkedList<>();
            
            for(PointScale p : app.getApplicationPointScales()){
                
                p.setCurrentValue(0.0);
                newUserPoints.add(p);
                
            }

            targetUser.setCurrentPoints(newUserPoints);
            targetUser.setAwardedBadges(new LinkedList<>());
            
            
        }
        System.out.println("USER REGISTERED");
        // Processing event for existing user   
        targetUser.setNbEvents(targetUser.getNbEvents() + 1);
        
        for(PointScale p : targetUser.getCurrentPoints()){
            
            if(p.getName().equals(e.getPointScaleToUpdate())){
                
                p.setCurrentValue(p.getCurrentValue() + e.getNewPoints());
                
            }
        }
        
        
        // Comparing event to application rules
            
        for(Rule r : app.getApplicationRules()){
            System.out.println("CHECKING RULE: " + r.getName());
            if(r.getPointScaleToCheck().getName().equals(e.getPointScaleToUpdate())){

                for(PointScale p : targetUser.getCurrentPoints()){
                    System.out.println("USER POINTSCALE: " + p.getName());
                    
                    if(p.getName().equals(e.getPointScaleToUpdate())){
                        
                        System.out.println("USER POINTSCALE MATCHED");
                        
                        if(p.getCurrentValue() >= r.getValueToReach()){
                            
                            Badge newAwardedBadge = r.getBadgeToAward();
                            
                            targetUser.getAwardedBadges().add(newAwardedBadge);
                            System.out.println("BADGEAWARDED");
                        }
                        
                    }
                    
                }

            }

        }
        
        
        usersRepository.save(targetUser);
        
        
    }
    
    
}
