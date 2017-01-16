package ch.heigvd.gamification.services;

import ch.heigvd.gamification.api.dao.ApplicationUserRepositoryJPA;
import ch.heigvd.gamification.api.dao.BadgeAwardRepositoryJPA;
import ch.heigvd.gamification.api.dao.CurrentPointsRepositoryJPA;
import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.model.ApplicationUser;
import ch.heigvd.gamification.model.BadgeAward;
import ch.heigvd.gamification.model.CurrentPoints;
import ch.heigvd.gamification.model.GameEvent;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.model.PointScale;
import ch.heigvd.gamification.model.Rule;
import java.util.HashMap;
import java.util.Map;
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
    @Autowired
    private GamifiedApplicationRepositoryJPA applicationsRepository;
    @Autowired
    private CurrentPointsRepositoryJPA pointsRepository;
    @Autowired
    private BadgeAwardRepositoryJPA awardedBadgesRepository;
    
        
    /**
     *
     * @param app
     * @param e
     */
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
            
            // Setting current values for each pointscale in application
            Map<String, CurrentPoints> newUserPoints = new HashMap<>();
            
            for(PointScale p : app.getApplicationPointScales()){
                
                CurrentPoints tmp = new CurrentPoints();
                tmp.setTargetPointScale(p);
                tmp.setCurrentValue(0.0);
                
                newUserPoints.put(p.getName(), tmp);
                pointsRepository.save(tmp);
                
            }

            targetUser.setCurrentPointsList(newUserPoints);
            
            // Setting empty badges map fo new user
            targetUser.setAwardedBadges(new HashMap<>());
            
            app.getApplicationUsers().add(targetUser);
            
            System.out.println("USER REGISTERED");
        }
        
        // Processing event for user   
        targetUser.setNbEvents(targetUser.getNbEvents() + 1);    
        
        // Comparing event to application rules
            
        for(Rule r : app.getApplicationRules()){
            System.out.println("CHECKING RULE: " + r.getName());
            
            if(r.getEventType().equals(e.getEventType())){
                
                // Badge awarding rule
                if(r.getBadgeToAward() != null){
                    
                    //Checking badge has not been already awarded
                    if(!targetUser.getAwardedBadges().containsKey(r.getBadgeToAward().getName())){
                        
                        //Checking if pointscale exists
                        if(targetUser.getCurrentPointsList().containsKey(r.getPointScaleToCheck().getName())){
                            
                            //Checking if awarding condition is met and awarding badge to user
                            if(targetUser.getCurrentPointsList().get(r.getPointScaleToCheck().getName()).getCurrentValue()>= r.getValueToReach()){
                                
                                BadgeAward newBadgeAward = new BadgeAward();
                                newBadgeAward.setTargetBadge(r.getBadgeToAward());
                                targetUser.getAwardedBadges().put(r.getBadgeToAward().getName(), newBadgeAward);
                                awardedBadgesRepository.save(newBadgeAward);
                                
                            }                           
                        }
                    }
                    
                    
                }
                // Points awarding rule
                else{
                    
                    //Checking if pointscale exists
                    if(targetUser.getCurrentPointsList().containsKey(r.getPointScaleToCheck().getName())){
                        
                        // Adding points to the pointscale, points value can be negative to remove points
                        
                        CurrentPoints oldPoints = targetUser.getCurrentPointsList().get(r.getPointScaleToCheck().getName());
                        oldPoints.setCurrentValue(oldPoints.getCurrentValue() + r.getPointsToAdd());
                        targetUser.getCurrentPointsList().replace(r.getPointScaleToCheck().getName(), oldPoints);
                        pointsRepository.save(oldPoints);
                                   
                    }
                        
                }
                
            }

        }
        
        // Saving the user in database
        usersRepository.save(targetUser);
        applicationsRepository.save(app);
        
    }
    
}
