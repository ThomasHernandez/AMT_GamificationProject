package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dto.AppCredentials;
import ch.heigvd.gamification.model.GamifiedApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class AuthApiController implements AuthApi{

    @Autowired
    private GamifiedApplicationRepositoryJPA applicationsRepository; 
    
    /**
     *
     * @param credentials
     * @return
     */
    @Override
    public ResponseEntity<String> authPost(@RequestBody AppCredentials credentials) {
        
        String appName = credentials.getAppName();
        String appPassword = credentials.getAppPassword();
        
        GamifiedApplication targetApp = applicationsRepository.findByName(appName);
        
        if(targetApp != null){
            
            String passwordHash = DigestUtils.md5DigestAsHex(appPassword.getBytes());
                        
            if(targetApp.getPasswordHash().equals(passwordHash)){
                return ResponseEntity.ok().body(targetApp.getAuthToken());
            }            
        }
        
        return ResponseEntity.badRequest().body("Bad credentials");
        
    }
    
    
    
}
