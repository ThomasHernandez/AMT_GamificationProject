/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dto.NewGamifiedApplication;
import ch.heigvd.gamification.model.GamifiedApplication;
import java.net.URI;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class RegistrationsApiController implements RegistrationsApi{

    
    private final GamifiedApplicationRepositoryJPA applicationsRepository;    

    public RegistrationsApiController(GamifiedApplicationRepositoryJPA applicationsRepository) {
        this.applicationsRepository = applicationsRepository;
    }
    
    @Override
    public ResponseEntity registrationsPost(NewGamifiedApplication newGamifiedApplication) {
        
        GamifiedApplication newA = newGamifiedApplicationToGamifiedApplication(newGamifiedApplication);
        
        if(applicationsRepository.findByName(newA.getName()) == null){
            
             applicationsRepository.save(newA);
             System.out.println("HASH STORED: " + newA.getPasswordHash());
             return ResponseEntity.created(URI.create("WOOPWOOP")).build();
             
             
             
        }
        else{
            
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
    
    
    
    private GamifiedApplication newGamifiedApplicationToGamifiedApplication(NewGamifiedApplication newApp){
        
        GamifiedApplication newA = new GamifiedApplication();
        newA.setName(newApp.getName());
        
        UUID newToken = UUID.randomUUID();
        newA.setAuthToken(newToken.toString());
        
        newA.setPasswordHash(DigestUtils.md5DigestAsHex(newApp.getPassword().getBytes()));
        
        return newA;
    }
}
