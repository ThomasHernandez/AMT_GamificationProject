package ch.heigvd.gamification.services;

import ch.heigvd.gamification.api.dao.GameEventsRepositoryJPA;
import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.model.GameEvent;
import ch.heigvd.gamification.model.GamifiedApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Antony
 */
@Service
public class EventProcessor {
    
    @Autowired 
    private GamifiedApplicationRepositoryJPA applicationsRepository;
    @Autowired
    private GameEventsRepositoryJPA eventsRepository;
    
    public void processEvent(String appToken, GameEvent e){
        
        if(applicationsRepository == null){
            
            System.out.println("APPNULLCACA");
        }
        
        GamifiedApplication app = applicationsRepository.findOneByAuthToken(appToken);
        System.out.println(appToken);
        if(app != null){
            
            System.out.println("COUCOU");
           
        }
        else{
            
            System.out.println("EPCACA");
        }
        
        
        
        
    }
    
    
}
