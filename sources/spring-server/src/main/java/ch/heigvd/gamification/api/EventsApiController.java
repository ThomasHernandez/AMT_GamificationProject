package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dto.NewGameEvent;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.services.EventProcessor;
import ch.heigvd.gamification.utils.ModelClassConverter;
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
    
    /**
     *
     * @param authToken
     * @param newGameEvent
     * @return
     */
    @Override
    public ResponseEntity eventsPost(@RequestHeader String authToken, @RequestBody NewGameEvent newGameEvent) {

        String targetUserId = newGameEvent.getAppUserId();
        GamifiedApplication targetApp = applicationsRepository.findByAuthToken(authToken);
        
        if(targetApp == null || targetUserId == null){
            
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            
        }
        eventProcessor.processEvent(targetApp, ModelClassConverter.newGameEventToGameEvent(newGameEvent));
        
        return ResponseEntity.accepted().build();
        

    } 
    
    
}
