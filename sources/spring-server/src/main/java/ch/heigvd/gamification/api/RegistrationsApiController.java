package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dto.NewGamifiedApplication;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.utils.ModelClassConverter;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class RegistrationsApiController implements RegistrationsApi{

    @Autowired
    private GamifiedApplicationRepositoryJPA applicationsRepository;    

    /**
     *
     * @param newGamifiedApplication
     * @return
     */
    @Override
    public ResponseEntity registrationsPost(@RequestBody NewGamifiedApplication newGamifiedApplication) {
        
        GamifiedApplication newA = ModelClassConverter.newGamifiedApplicationToGamifiedApplication(newGamifiedApplication);
        
        if(applicationsRepository.findByName(newA.getName()) == null){
            
             applicationsRepository.save(newA);
             
             return ResponseEntity.created(URI.create("")).build();

        }
        else{
            
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
    
    
}
