/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.NewPointScale;
import ch.heigvd.gamification.api.dto.PointScaleToClient;
import ch.heigvd.gamification.model.PointScale;
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
    public ResponseEntity<List<PointScaleToClient>> pointscalesGet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<Void> pointscalesIdDelete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<PointScaleToClient> pointscalesIdGet(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<PointScaleToClient> pointscalesPost(NewPointScale newPointScale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
