package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dto.GamifiedApplicationToClient;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.utils.ModelClassConverter;
import java.util.LinkedList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class ApplicationsApiController implements ApplicationsApi{

    private final GamifiedApplicationRepositoryJPA applicationsRepository;

    /**
     *
     * @param applicationsRepository
     */
    public ApplicationsApiController(GamifiedApplicationRepositoryJPA applicationsRepository) {
        this.applicationsRepository = applicationsRepository;
    }
    
    /**
     *
     * @return
     */
    @Override
    public ResponseEntity<List<GamifiedApplicationToClient>> applicationsGet() {
        
        List<GamifiedApplication> result = applicationsRepository.findAll();
        
        List<GamifiedApplicationToClient> resultToClient = new LinkedList<>();
        
        for(GamifiedApplication g : result){
            resultToClient.add(ModelClassConverter.gamifiedApplicationToGamifiedApplicationToClient(g));
        }
        
        return ResponseEntity.ok().body(resultToClient);
    }

    /**
     *
     * @param id
     * @return
     */
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

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<GamifiedApplicationToClient> applicationsIdGet(@PathVariable Long id) {
        
        if(applicationsRepository.findOne(id) != null){
            
            return ResponseEntity.ok().body(ModelClassConverter.gamifiedApplicationToGamifiedApplicationToClient(applicationsRepository.findOne(id)));
        }
        else{
            return ResponseEntity.status(404).body(null);
        }
        
        
    }
    
}
