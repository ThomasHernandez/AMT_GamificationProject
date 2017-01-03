package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.ApplicationUserRepositoryJPA;
import ch.heigvd.gamification.api.dto.ApplicationUserToClient;
import ch.heigvd.gamification.model.ApplicationUser;
import ch.heigvd.gamification.utils.ModelClassConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class UsersApiController implements UsersApi{

    @Autowired
    private  ApplicationUserRepositoryJPA appUsersRepository;

    @Override
    public ResponseEntity<ApplicationUserToClient> usersIdInApplicationGet(String authToken, String idInApplication) {
        
        
        
        ApplicationUser user = appUsersRepository.findByApplicationAuthTokenAndIdInGamifiedApplication(authToken, idInApplication);
            
        if(user != null){
            
            return ResponseEntity.ok().body(applicationUserToApplicationUserToClient(user));
            
        }
        else{
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            
        }
        
    }
    
    private ApplicationUserToClient applicationUserToApplicationUserToClient(ApplicationUser appUser){
        
        ApplicationUserToClient appUserToClient = new ApplicationUserToClient();
        
        appUserToClient.setApplicationName(appUser.getApplication().getName());
        appUserToClient.setAwardedBadges(ModelClassConverter.badgeListToBadgeToClientList(appUser.getAwardedBadges()));
        appUserToClient.setCurrentPoints(ModelClassConverter.pointScaleListToPointScaleToClientList(appUser.getCurrentPoints()));
        appUserToClient.setId(appUser.getId());
        appUserToClient.setIdInApplication(appUser.getIdInGamifiedApplication());
        appUserToClient.setNbEvents(appUser.getNbEvents());
        
        return appUserToClient;
        
    }
    
    
    
}
