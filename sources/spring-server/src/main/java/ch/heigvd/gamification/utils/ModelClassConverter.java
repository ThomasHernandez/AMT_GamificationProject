package ch.heigvd.gamification.utils;

import ch.heigvd.gamification.api.dto.ApplicationUserToClient;
import ch.heigvd.gamification.api.dto.BadgeToClient;
import ch.heigvd.gamification.api.dto.GamifiedApplicationToClient;
import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.api.dto.NewGameEvent;
import ch.heigvd.gamification.api.dto.NewGamifiedApplication;
import ch.heigvd.gamification.api.dto.NewPointScale;
import ch.heigvd.gamification.api.dto.NewRule;
import ch.heigvd.gamification.api.dto.PointScaleToClient;
import ch.heigvd.gamification.api.dto.RuleToClient;
import ch.heigvd.gamification.model.ApplicationUser;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.GameEvent;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.model.PointScale;
import ch.heigvd.gamification.model.Rule;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.springframework.util.DigestUtils;

/**
 *
 * @author Antony
 */
public class ModelClassConverter {
    
    
    public static GamifiedApplicationToClient gamifiedApplicationToGamifiedApplicationToClient(GamifiedApplication app){
        
        return new GamifiedApplicationToClient().name(app.getName())
                                                .authToken(app.getAuthToken())
                                                .badges(badgeListToBadgeToClientList(app.getApplicationBadges()))
                                                .pointScales(pointScaleListToPointScaleToClientList(app.getApplicationPointScales()))
                                                .rules(rulesListToRuleToClientList(app.getApplicationRules()));
        
        
        
    }
    
    public static List<RuleToClient> rulesListToRuleToClientList(List<Rule> rules){
        
        List<RuleToClient> rulesToClient = new LinkedList<>();
        
        if(rules != null){
            
            for(Rule r : rules){
            
                RuleToClient tmp =  new RuleToClient().name(r.getName())
                                                      .description(r.getDescription())
                                                      .eventType(r.getEventType())
                                                      .pointScaleName(r.getPointScaleToCheck().getName())
                                                      .valueToReach(r.getValueToReach())
                                                      .badgeName(r.getBadgeToAward().getName());

                rulesToClient.add(tmp);
            }
        }
        
        return rulesToClient;
        
    }
    
    public static List<BadgeToClient> badgeListToBadgeToClientList(List<Badge> badges){
        
        List<BadgeToClient> badgesToClient = new LinkedList<>();
        
        if(badges != null){
            
            for(Badge b : badges){
            
                BadgeToClient tmp =  new BadgeToClient().id(b.getId())
                                        .name(b.getName())
                                        .description(b.getDescription())
                                        .imageURI(b.getImageURI());

                badgesToClient.add(tmp);
            }
        }
        
        return badgesToClient;
        
    }
    
    public static BadgeToClient badgeToBadgeToClient(Badge badge){
        
        return new BadgeToClient().id(badge.getId())
                                    .name(badge.getName())
                                    .description(badge.getDescription())
                                    .imageURI(badge.getImageURI());
        
    }
    
    public static Badge newBadgeToBadge(NewBadge newBadge){
        
        Badge newB = new Badge();
        newB.setName(newBadge.getName());
        newB.setDescription(newBadge.getDescription());
        newB.setImageURI(newBadge.getImageURI());
        
        return newB;
        
    }
 
    
    public static GameEvent newGameEventToGameEvent(NewGameEvent newGameEvent){
        
        GameEvent newG = new GameEvent();
        
        newG.setAppUserId(newGameEvent.getAppUserId());
        newG.setPointScaleToUpdate(newGameEvent.getPointScaleToUpdateName());
        newG.setEventType(newGameEvent.getEventType());
        newG.setNewPoints(newGameEvent.getNewPoints());
        
        return newG;
    }
    
    
    public static PointScaleToClient pointScaleToPointScaleToClient(PointScale pointScale){
        
        return new PointScaleToClient().id(pointScale.getId())
                                    .name(pointScale.getName())
                                    .description(pointScale.getDescription())
                                    .lowerBound(pointScale.getLowerBound())
                                    .upperBound(pointScale.getUpperBound())
                                    .isIntegerScale(pointScale.getIsIntegerScale())
                                    .unit(pointScale.getUnit())
                                    .currentValue(pointScale.getCurrentValue());
        
    }
    
    public static List<PointScaleToClient> pointScaleListToPointScaleToClientList(List<PointScale> pointScales){
        
        List<PointScaleToClient> pointScalesToClient = new LinkedList<>();
        
        if(pointScales != null){
            
            for(PointScale p : pointScales){
            
                PointScaleToClient tmp =  new PointScaleToClient().id(p.getId())
                                        .name(p.getName())
                                        .description(p.getDescription())
                                        .isIntegerScale(p.getIsIntegerScale())
                                        .currentValue(p.getCurrentValue())
                                        .lowerBound(p.getLowerBound())
                                        .upperBound(p.getUpperBound())
                                        .unit(p.getUnit());

                pointScalesToClient.add(tmp);
            }
        }
        
        return pointScalesToClient;
        
    }
    
    public static PointScale newPointScaleToPointScale(NewPointScale newPointScale){
        
        PointScale newP = new PointScale();
        newP.setName(newPointScale.getName());
        newP.setDescription(newPointScale.getDescription());
        newP.setLowerBound(newPointScale.getLowerBound());
        newP.setUpperBound(newPointScale.getUpperBound());
        newP.setIsIntegerScale(newPointScale.getIsIntegerScale());
        newP.setUnit(newPointScale.getUnit());
        newP.setCurrentValue(0.0);
        
        return newP;
        
    }
    
    public static GamifiedApplication newGamifiedApplicationToGamifiedApplication(NewGamifiedApplication newApp){
        
        GamifiedApplication newA = new GamifiedApplication();
        newA.setName(newApp.getName());
        
        UUID newToken = UUID.randomUUID();
        newA.setAuthToken(newToken.toString());
        
        newA.setPasswordHash(DigestUtils.md5DigestAsHex(newApp.getPassword().getBytes()));
        
        return newA;
    }
    
    public static ApplicationUserToClient applicationUserToApplicationUserToClient(ApplicationUser appUser){
        
        ApplicationUserToClient appUserToClient = new ApplicationUserToClient();
        
        appUserToClient.setApplicationName(appUser.getApplication().getName());
        appUserToClient.setAwardedBadges(badgeListToBadgeToClientList(appUser.getAwardedBadges()));
        appUserToClient.setCurrentPoints(pointScaleListToPointScaleToClientList(appUser.getCurrentPoints()));
        appUserToClient.setId(appUser.getId());
        appUserToClient.setIdInApplication(appUser.getIdInGamifiedApplication());
        appUserToClient.setNbEvents(appUser.getNbEvents());
        
        return appUserToClient;
        
    }
    
    public static Rule newRuleToRule(NewRule newRule){
        
        Rule rule = new Rule();
        
        rule.setName(newRule.getName());
        rule.setDescription(newRule.getDescription());
        rule.setEventType(newRule.getEventType());
        rule.setValueToReach(newRule.getValueToReach());
        
        return rule;
        
    }
    
}
