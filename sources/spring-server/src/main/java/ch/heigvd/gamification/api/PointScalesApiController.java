/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.PointScalesRepositoryJPA;
import ch.heigvd.gamification.api.dto.NewPointScale;
import ch.heigvd.gamification.api.dto.PointScaleToClient;
import ch.heigvd.gamification.model.PointScale;
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
public class PointScalesApiController implements PointscalesApi{

    @Autowired
    private PointScalesRepositoryJPA pointScalesRepository;
    
    @Override
    public ResponseEntity<List<PointScaleToClient>> pointscalesGet() {
        List<PointScale> result = pointScalesRepository.findAll();
        
        List<PointScaleToClient> resultToClient = new LinkedList<>();
        
        for(PointScale p : result){
            resultToClient.add(pointScaleToPointScaleToClient(p));
        }
        return ResponseEntity.ok().body(resultToClient);

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
        PointScale newP = newPointScaleToPointScale(newPointScale);
        pointScalesRepository.save(newP);
        return ResponseEntity.ok().body(pointScaleToPointScaleToClient(newP));
        
    }

    
     //Helper methods
    
    private PointScaleToClient pointScaleToPointScaleToClient(PointScale pointScale){
        
        return new PointScaleToClient().id(pointScale.getId())
                                    .name(pointScale.getName())
                                    .description(pointScale.getDescription())
                                    .lowerBound(pointScale.getLowerBound())
                                    .upperBound(pointScale.getUpperBound())
                                    .isIntegerScale(pointScale.getIsIntegerScale())
                                    .unit(pointScale.getUnit());
        
    }
    
    private PointScale newPointScaleToPointScale(NewPointScale newPointScale){
        
        PointScale newP = new PointScale();
        newP.setName(newPointScale.getName());
        newP.setDescription(newPointScale.getDescription());
        newP.setLowerBound(newPointScale.getLowerBound());
        newP.setUpperBound(newPointScale.getUpperBound());
        newP.setIsIntegerScale(newPointScale.getIsIntegerScale());
        newP.setUnit(newPointScale.getUnit());
        
        return newP;
        
    }
    
    
}
