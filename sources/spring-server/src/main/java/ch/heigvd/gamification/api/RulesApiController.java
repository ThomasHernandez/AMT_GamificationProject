package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dao.BadgesRepositoryJPA;
import ch.heigvd.gamification.api.dao.GamifiedApplicationRepositoryJPA;
import ch.heigvd.gamification.api.dao.PointScalesRepositoryJPA;
import ch.heigvd.gamification.api.dao.RulesRepositoryJPA;
import ch.heigvd.gamification.api.dto.NewRule;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.model.PointScale;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.utils.ModelClassConverter;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antony
 */
@RestController
public class RulesApiController implements RulesApi{

    @Autowired
    private GamifiedApplicationRepositoryJPA applicationsRepository;
    @Autowired
    private PointScalesRepositoryJPA pointScalesRepository;
    @Autowired
    private BadgesRepositoryJPA badgesRepository;
    @Autowired
    private RulesRepositoryJPA rulesRepository;
    
    /**
     *
     * @param authToken
     * @param newRule
     * @return
     */
    @Override
    public ResponseEntity<Void> rulesPost(@RequestHeader String authToken, @RequestBody NewRule newRule) {
        
        GamifiedApplication targetApp = applicationsRepository.findByAuthToken(authToken);
        
        if(targetApp != null){
            
                
            Rule ruleToSave = newRuleToRule(newRule);

            rulesRepository.save(ruleToSave);
            targetApp.getApplicationRules().add(ruleToSave);
            applicationsRepository.save(targetApp);

            return ResponseEntity.created(URI.create("")).build();
                
            
        }
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        
    }
    
    private Rule newRuleToRule(NewRule newRule) {

        Rule rule = new Rule();

        rule.setName(newRule.getName());
        rule.setDescription(newRule.getDescription());
        rule.setEventType(newRule.getEventType());
        rule.setPointScaleToCheck(pointScalesRepository.findByName(newRule.getPointScaleName()));
        rule.setPointsToAdd(newRule.getPointsToAdd());
        if(!newRule.getBadgeName().isEmpty()){
            rule.setBadgeToAward(badgesRepository.findByName(newRule.getBadgeName()));
        }
        rule.setValueToReach(newRule.getValueToReach());

        return rule;

    }
    
    
    
}
