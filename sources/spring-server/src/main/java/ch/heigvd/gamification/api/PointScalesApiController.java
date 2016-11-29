/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.NewPointScale;
import ch.heigvd.gamification.api.dto.PointScale;
import java.util.LinkedList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class PointScalesApiController implements PointscalesApi{

    @Override
    public ResponseEntity<List<PointScale>> pointscalesGet() {
        List<PointScale> result = new LinkedList<>();
        result.add(new PointScale().name("COCO"));
        
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> pointscalesIdDelete(Long id) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<PointScale> pointscalesIdGet(Long id) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<PointScale> pointscalesPost(NewPointScale pointScale) {
        return ResponseEntity.ok(null);
    }
    
}
