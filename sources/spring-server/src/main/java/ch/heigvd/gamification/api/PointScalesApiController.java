package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dao.PointScalesRepositoryJPA;
import ch.heigvd.gamification.api.dto.NewPointScale;
import ch.heigvd.gamification.api.dto.PointScaleToClient;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.model.PointScale;
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
public class PointScalesApiController implements PointscalesApi{

    @Autowired
    private PointScalesRepositoryJPA pointScalesRepository;
    @Autowired
    private GamifiedApplicationRepositoryJPA applicationsRepository;
    
    /**
     *
     * @return
     */
    @Override
    public ResponseEntity<List<PointScaleToClient>> pointscalesGet() {
        List<PointScale> result = pointScalesRepository.findAll();
        
        List<PointScaleToClient> resultToClient = new LinkedList<>();
        
        for(PointScale p : result){
            resultToClient.add(ModelClassConverter.pointScaleToPointScaleToClient(p));
        }
        return ResponseEntity.ok().body(resultToClient);

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Void> pointscalesIdDelete(@PathVariable Long id) {
        
        if(pointScalesRepository.findOne(id) != null){
            pointScalesRepository.delete(id);
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
    public ResponseEntity<PointScaleToClient> pointscalesIdGet(@PathVariable Long id) {
        if(pointScalesRepository.findOne(id) != null){
            
            return ResponseEntity.ok().body(ModelClassConverter.pointScaleToPointScaleToClient(pointScalesRepository.findOne(id)));
        }
        else{
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     *
     * @param authToken
     * @param newPointScale
     * @return
     */
    @Override
    public ResponseEntity<PointScaleToClient> pointscalesPost(@RequestHeader String authToken, @RequestBody NewPointScale newPointScale) {
        
        GamifiedApplication targetApp = applicationsRepository.findByAuthToken(authToken);
        
        if(targetApp != null){
            
            if(pointScalesRepository.findByName(newPointScale.getName()) == null){
                
                PointScale newP = ModelClassConverter.newPointScaleToPointScale(newPointScale);
                pointScalesRepository.save(newP);
                targetApp.getApplicationPointScales().add(newP);
                applicationsRepository.save(targetApp);
                return ResponseEntity.ok().body(ModelClassConverter.pointScaleToPointScaleToClient(newP));
                
            }
        }
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        
        
    }
    
}
