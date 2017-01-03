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
    
    @Override
    public ResponseEntity<String> authPost(@RequestBody AppCredentials credentials) {
        
        String appName = credentials.getAppName();
        String appPassword = credentials.getAppPassword();
        
        GamifiedApplication targetApp = applicationsRepository.findByName(appName);
        
        if(targetApp != null){
            
            String passwordHash = DigestUtils.md5DigestAsHex(appPassword.getBytes());
            
            System.out.println("HASH TESTED: " + passwordHash);
            
            if(targetApp.getPasswordHash().equals(passwordHash)){
                System.out.println("YO GOOD CREDENTIALS");
                return ResponseEntity.ok().body(targetApp.getAuthToken());
            }            
        }
        
        return ResponseEntity.badRequest().body("Bad credentials");
        
    }
    
    
    
}
