package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.BadgesRepositoryJPA;
import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dto.BadgeToClient;
import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.utils.ModelClassConverter;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class BadgesApiController implements BadgesApi{

    @Autowired
    private BadgesRepositoryJPA badgesRepository;
    @Autowired
    private GamifiedApplicationRepositoryJPA applicationsRepository;
    
    /**
     *
     * @return
     */
    @Override
    public ResponseEntity<List<BadgeToClient>> badgesGet() {
        List<Badge> result = badgesRepository.findAll();
        
        List<BadgeToClient> resultToClient = new LinkedList<>();
        
        for(Badge b : result){
            resultToClient.add(ModelClassConverter.badgeToBadgeToClient(b));
        }
        
        return ResponseEntity.ok().body(resultToClient);
        
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Void> badgesIdDelete(@PathVariable Long id) {
        
        if(badgesRepository.findOne(id) != null){
            badgesRepository.delete(id);
            return ResponseEntity.status(204).body(null);
        }
        else{
            return ResponseEntity.status(404).body(null);
        }
        
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<BadgeToClient> badgesIdGet(@PathVariable Long id) {
        if(badgesRepository.findOne(id) != null){
            
            return ResponseEntity.ok().body(ModelClassConverter.badgeToBadgeToClient(badgesRepository.findOne(id)));
        }
        else{
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     *
     * @param authToken
     * @param newBadge
     * @return
     */
    @Override
    public ResponseEntity<BadgeToClient> badgesPost(@RequestHeader String authToken, @RequestBody NewBadge newBadge) {
        
        GamifiedApplication targetApp = applicationsRepository.findByAuthToken(authToken);
        
        if(targetApp != null){
            
            if(badgesRepository.findByName(newBadge.getName()) == null){
                
                Badge newB = ModelClassConverter.newBadgeToBadge(newBadge);
                badgesRepository.save(newB);
                targetApp.getApplicationBadges().add(newB);
                applicationsRepository.save(targetApp);
                return ResponseEntity.ok().body(ModelClassConverter.badgeToBadgeToClient(newB));
                
            }
        }
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        
        
    }
    
}
