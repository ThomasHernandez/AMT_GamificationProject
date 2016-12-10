package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.GameEventsRepositoryJPA;
import ch.heigvd.gamification.api.dto.NewGameEvent;
import ch.heigvd.gamification.model.GameEvent;
import ch.heigvd.gamification.services.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class EventsApiController implements EventsApi{

    @Autowired 
    private GameEventsRepositoryJPA eventsRepository;
    
    private EventProcessor ep = new EventProcessor();
    
    
    
    @Override
    public ResponseEntity<NewGameEvent> eventsPost(@RequestHeader String authToken, NewGameEvent newGameEvent) {

        GameEvent newGE = newGameEventToGameEvent(newGameEvent);
        
        eventsRepository.save(newGE);
                
        //ep.processEvent(authToken, newGE);
        
        return ResponseEntity.ok().body(GameEventToNewGameEvent(newGE));
        

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
