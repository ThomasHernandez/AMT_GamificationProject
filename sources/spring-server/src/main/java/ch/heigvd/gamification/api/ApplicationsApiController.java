package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dto.BadgeToClient;
import ch.heigvd.gamification.api.dto.GamifiedApplicationToClient;
import ch.heigvd.gamification.api.dto.NewGamifiedApplication;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.GamifiedApplication;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class ApplicationsApiController implements ApplicationsApi{

    @Autowired
    GamifiedApplicationRepositoryJPA applicationsRepository;
    
    @Override
    public ResponseEntity<List<GamifiedApplicationToClient>> applicationsGet() {
        
        List<GamifiedApplication> result = applicationsRepository.findAll();
        
        List<GamifiedApplicationToClient> resultToClient = new LinkedList<>();
        
        for(GamifiedApplication g : result){
            resultToClient.add(gamifiedApplicationToGamifiedApplicationToClient(g));
        }
        
        return ResponseEntity.ok().body(resultToClient);
    }

    @Override
    public ResponseEntity<Void> applicationsIdDelete(@PathVariable Long id) {
        
        if(applicationsRepository.findOne(id) != null){
            applicationsRepository.delete(id);
            return ResponseEntity.status(204).body(null);
        }
        else{
            return ResponseEntity.status(404).body(null);
        }

    }

    @Override
    public ResponseEntity<GamifiedApplicationToClient> applicationsIdGet(@PathVariable Long id) {
        
        if(applicationsRepository.findOne(id) != null){
            
            return ResponseEntity.ok().body(gamifiedApplicationToGamifiedApplicationToClient(applicationsRepository.findOne(id)));
        }
        else{
            return ResponseEntity.status(404).body(null);
        }
        
        
    }

    @Override
    public ResponseEntity<GamifiedApplicationToClient> applicationsPost(NewGamifiedApplication newGamifiedApplication) {
        
        GamifiedApplication newA = newGamifiedApplicationToGamifiedApplication(newGamifiedApplication);
        
        applicationsRepository.save(newA);
                
        return ResponseEntity.ok().body(gamifiedApplicationToGamifiedApplicationToClient(newA));


    }

    
    
    
    private GamifiedApplication newGamifiedApplicationToGamifiedApplication(NewGamifiedApplication newApp){
        
        GamifiedApplication newA = new GamifiedApplication();
        newA.setName(newApp.getName());
        
        UUID newToken = UUID.randomUUID();
        newA.setAuthToken(newToken.toString());
        
        return newA;
    }
    
    private GamifiedApplicationToClient gamifiedApplicationToGamifiedApplicationToClient(GamifiedApplication app){
        
        return new GamifiedApplicationToClient().name(app.getName())
                                                .authToken(app.getAuthToken())
                                                .badges(badgeListToBadgeToClientList(app.getApplicationBadges()));
        
    }
    
    private List<BadgeToClient> badgeListToBadgeToClientList(List<Badge> badges){
        
        List<BadgeToClient> badgesToClient = new LinkedList<>();
        
        if(badges != null){
            
            for(Badge b : badges){
            
                BadgeToClient tmp =  new BadgeToClient().id(b.getId())
                                        .name(b.getName())
                                        .description(b.getDescription())
                                        .imageURI(b.getImageURI());

                badgesToClient.add(tmp);
            }
        }
        
        return badgesToClient;
        
    }
    
    
}
