package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.ApplicationUserRepositoryJPA;
import ch.heigvd.gamification.api.dto.ApplicationUserToClient;
import ch.heigvd.gamification.model.ApplicationUser;
import ch.heigvd.gamification.utils.ModelClassConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class UsersApiController implements UsersApi{

    @Autowired
    private  ApplicationUserRepositoryJPA appUsersRepository;

    /**
     *
     * @param authToken
     * @param idInApplication
     * @return
     */
    @Override
    public ResponseEntity<ApplicationUserToClient> usersIdInApplicationGet(@RequestHeader String authToken, @PathVariable String idInApplication) {
        
        
        
        ApplicationUser user = appUsersRepository.findByApplicationAuthTokenAndIdInGamifiedApplication(authToken, idInApplication);
            
        if(user != null){
            
            return ResponseEntity.ok().body(ModelClassConverter.applicationUserToApplicationUserToClient(user));
            
        }
        else{
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            
        }
        
    }
    
    
}
