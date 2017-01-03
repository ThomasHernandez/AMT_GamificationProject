package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dto.NewGameEvent;
import ch.heigvd.gamification.model.GameEvent;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.services.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class EventsApiController implements EventsApi{

    @Autowired
    private GamifiedApplicationRepositoryJPA applicationsRepository;
    @Autowired
    private EventProcessor eventProcessor;

    
    
    @Override
    public ResponseEntity eventsPost(@RequestHeader String authToken, @RequestBody NewGameEvent newGameEvent) {

        
        String targetUserId = newGameEvent.getAppUserId();
        GamifiedApplication targetApp = applicationsRepository.findByAuthToken(authToken);
        
        if(targetApp == null || targetUserId == null){
            
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            
        }
        eventProcessor.processEvent(targetApp, newGameEventToGameEvent(newGameEvent));
        
        return ResponseEntity.accepted().build();
        

    } 
    
    
    
    public static GameEvent newGameEventToGameEvent(NewGameEvent newGameEvent){
        
        GameEvent newG = new GameEvent();
        
        newG.setAppUserId(newGameEvent.getAppUserId());
        newG.setEventType(newGameEvent.getData());
        newG.setData(newGameEvent.getData());
        
        return newG;
    }
    
    public static NewGameEvent GameEventToNewGameEvent(GameEvent gameEvent){
        
        return new NewGameEvent().appUserId(gameEvent.getAppUserId())
                                 .eventType(gameEvent.getEventType())
                                 .data(gameEvent.getData());
    }
    
    
}
