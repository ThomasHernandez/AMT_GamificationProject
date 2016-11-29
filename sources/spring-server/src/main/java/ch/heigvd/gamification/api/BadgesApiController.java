/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.NewBadge;
import java.util.LinkedList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class BadgesApiController implements BadgesApi{

    @Override
    public ResponseEntity<List<Badge>> badgesGet() {
        List<Badge> result = new LinkedList<>();
        result.add(new Badge().name("COCO"));
        
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> badgesIdDelete(Long id) {
         return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Badge> badgesIdGet(Long id) {
         return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Badge> badgesPost(NewBadge pet) {
        return ResponseEntity.ok(null);
    }
    
}
