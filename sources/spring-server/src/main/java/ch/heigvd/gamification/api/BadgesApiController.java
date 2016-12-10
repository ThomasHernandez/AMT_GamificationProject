package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.BadgesRepositoryJPA;
import ch.heigvd.gamification.api.dto.BadgeToClient;

import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.model.Badge;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @Override
    public ResponseEntity<List<BadgeToClient>> badgesGet() {
        List<Badge> result = badgesRepository.findAll();
        
        List<BadgeToClient> resultToClient = new LinkedList<>();
        
        for(Badge b : result){
            resultToClient.add(badgeToBadgeToClient(b));
        }
        
        return ResponseEntity.ok().body(resultToClient);
        
    }

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

    @Override
    public ResponseEntity<BadgeToClient> badgesIdGet(@PathVariable Long id) {
        if(badgesRepository.findOne(id) != null){
            
            return ResponseEntity.ok().body(badgeToBadgeToClient(badgesRepository.findOne(id)));
        }
        else{
            return ResponseEntity.status(404).body(null);
        }
    }

    @Override
    public ResponseEntity<BadgeToClient> badgesPost(@RequestHeader String authToken, NewBadge newBadge) {

        
        Badge newB = newBadgeToBadge(newBadge);
        
        badgesRepository.save(newB);
        
        return ResponseEntity.ok().body(badgeToBadgeToClient(newB));
        
    }
    
    //Helper methods for DTO conversion
    
    private BadgeToClient badgeToBadgeToClient(Badge badge){
        
        return new BadgeToClient().id(badge.getId())
                                    .name(badge.getName())
                                    .description(badge.getDescription())
                                    .imageURI(badge.getImageURI());
        
    }
    
    private Badge newBadgeToBadge(NewBadge newBadge){
        
        Badge newB = new Badge();
        newB.setName(newBadge.getName());
        newB.setDescription(newBadge.getDescription());
        newB.setImageURI(newBadge.getImageURI());
        
        return newB;
        
    }
    
    
}
