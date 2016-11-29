/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.BadgesRepositoryJPA;
import ch.heigvd.gamification.api.dto.BadgeToClient;

import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.model.Badge;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class BadgesApiController implements BadgesApi{

    @Autowired
    private BadgesRepositoryJPA badgeRepository;
    
    @Override
    public ResponseEntity<List<BadgeToClient>> badgesGet() {
        List<Badge> result = badgeRepository.findAll();
        
        List<BadgeToClient> resultToClient = new LinkedList<>();
        
        for(Badge b : result){
            resultToClient.add(badgeToBadgeToClient(b));
        }
        
        return ResponseEntity.ok().body(resultToClient);
        
    }

    @Override
    public ResponseEntity<Void> badgesIdDelete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<BadgeToClient> badgesIdGet(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<BadgeToClient> badgesPost(NewBadge newBadge) {
        
        Badge newB = newBadgeToBadge(newBadge);
        
        badgeRepository.save(newB);
        
        return ResponseEntity.ok().body(badgeToBadgeToClient(newB));
        
    }

   
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
