/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.ApplicationUserRepositoryJPA;
import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dto.ApplicationUserToClient;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.utils.ModelClassConverter;
import java.util.List;
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
//@RestController
public class LeaderBoardApiController implements LeaderboardApi{

    @Autowired
    private GamifiedApplicationRepositoryJPA applicationsRepository;
    @Autowired
    private ApplicationUserRepositoryJPA usersRepository;
    
    @Override
    public ResponseEntity<List<ApplicationUserToClient>> leaderboardPointScaleNameGet(@RequestHeader String authToken, @PathVariable String pointScaleName) {
        
        GamifiedApplication targetApp = applicationsRepository.findByAuthToken(authToken);
        if(targetApp != null){

//            return ResponseEntity.ok(ModelClassConverter.applicationUserListToApplicationUserToClientList
//                                        (usersRepository.findByApplicationAuthTokenAndCurrentPointsNameOrderByCurrentPointsCurrentValueDesc(authToken, pointScaleName)));
                
            
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        
        
        
        
    }
    
    
    
}
