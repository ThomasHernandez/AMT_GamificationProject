package ch.heigvd.gamification.services;

import ch.heigvd.gamification.api.dao.ApplicationUserRepositoryJPA;
import ch.heigvd.gamification.model.ApplicationUser;
import ch.heigvd.gamification.model.GameEvent;
import ch.heigvd.gamification.model.GamifiedApplication;
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
        
        if(targetUser == null){
            
            targetUser = new ApplicationUser();
            targetUser.setApplication(app);
            targetUser.setIdInGamifiedApplication(e.getAppUserId());
            targetUser.setNbEvents(1);
            
            usersRepository.save(targetUser);
            
            ApplicationUser tmp = usersRepository.findByApplicationNameAndIdInGamifiedApplication(app.getName(), e.getAppUserId());
            
            if(tmp == null){
                
                System.out.println("SHIIIIT");
            }
            else{
                System.out.println("USER SAVED with app name: " + tmp.getApplication().getName());
                
            }
                    
            
        }
        else{
            
            targetUser.setNbEvents(targetUser.getNbEvents() + 1);
            
        }
        
           
        
        
        
    }
    
    
}
