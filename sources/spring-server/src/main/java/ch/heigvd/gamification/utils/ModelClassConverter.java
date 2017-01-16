package ch.heigvd.gamification.utils;

import ch.heigvd.gamification.api.dto.ApplicationUserToClient;
import ch.heigvd.gamification.api.dto.BadgeToClient;
import ch.heigvd.gamification.api.dto.CurrentPointsToClient;
import ch.heigvd.gamification.api.dto.GamifiedApplicationToClient;
import ch.heigvd.gamification.api.dto.NewBadge;
import ch.heigvd.gamification.api.dto.NewGameEvent;
import ch.heigvd.gamification.api.dto.NewGamifiedApplication;
import ch.heigvd.gamification.api.dto.NewPointScale;
import ch.heigvd.gamification.api.dto.PointScaleToClient;
import ch.heigvd.gamification.api.dto.RuleToClient;
import ch.heigvd.gamification.model.ApplicationUser;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.BadgeAward;
import ch.heigvd.gamification.model.CurrentPoints;
import ch.heigvd.gamification.model.GameEvent;
import ch.heigvd.gamification.model.GamifiedApplication;
import ch.heigvd.gamification.model.PointScale;
import ch.heigvd.gamification.model.Rule;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.util.DigestUtils;

/**
 *
 * @author Antony
 */
public class ModelClassConverter {

    /**
     *
     * @param app
     * @return
     */
    public static GamifiedApplicationToClient gamifiedApplicationToGamifiedApplicationToClient(GamifiedApplication app) {

        return new GamifiedApplicationToClient().name(app.getName())
                .authToken(app.getAuthToken())
                .badges(badgeListToBadgeToClientList(app.getApplicationBadges()))
                .pointScales(pointScaleListToPointScaleToClientList(app.getApplicationPointScales()))
                .rules(rulesListToRuleToClientList(app.getApplicationRules()));

    }

    /**
     *
     * @param rules
     * @return
     */
    public static List<RuleToClient> rulesListToRuleToClientList(List<Rule> rules) {

        List<RuleToClient> rulesToClient = new LinkedList<>();

        if (rules != null) {

            for (Rule r : rules) {

                RuleToClient tmp = new RuleToClient().name(r.getName())
                        .description(r.getDescription())
                        .eventType(r.getEventType())
                        .pointScaleName(r.getPointScaleToCheck().getName())
                        .pointsToAdd(r.getPointsToAdd())
                        .valueToReach(r.getValueToReach());
                
                if(r.getBadgeToAward() != null){
                    tmp.setBadgeName(r.getBadgeToAward().getName());
                }

                rulesToClient.add(tmp);
            }
        }

        return rulesToClient;

    }

    /**
     *
     * @param badges
     * @return
     */
    public static List<BadgeToClient> badgeListToBadgeToClientList(List<Badge> badges) {

        List<BadgeToClient> badgesToClient = new LinkedList<>();

        if (badges != null) {

            for (Badge b : badges) {

                BadgeToClient tmp = new BadgeToClient().id(b.getId())
                        .name(b.getName())
                        .description(b.getDescription())
                        .imageURI(b.getImageURI());

                badgesToClient.add(tmp);
            }
        }

        return badgesToClient;

    }
    
    /**
     *
     * @param badges
     * @return
     */
    public static List<BadgeToClient> badgeMapToBadgeToClientList(Map<String,BadgeAward> badges) {

        List<BadgeToClient> badgesToClient = new LinkedList<>();

        if (badges != null) {

            badges.forEach((k,b) -> {

                BadgeToClient tmp = new BadgeToClient().id(b.getId())
                        .name(b.getTargetBadge().getName())
                        .description(b.getTargetBadge().getDescription())
                        .imageURI(b.getTargetBadge().getImageURI());

                badgesToClient.add(tmp);
                
            });
        }

        return badgesToClient;

    }

    /**
     *
     * @param badge
     * @return
     */
    public static BadgeToClient badgeToBadgeToClient(Badge badge) {

        return new BadgeToClient().id(badge.getId())
                .name(badge.getName())
                .description(badge.getDescription())
                .imageURI(badge.getImageURI());

    }

    /**
     *
     * @param newBadge
     * @return
     */
    public static Badge newBadgeToBadge(NewBadge newBadge) {

        Badge newB = new Badge();
        newB.setName(newBadge.getName());
        newB.setDescription(newBadge.getDescription());
        newB.setImageURI(newBadge.getImageURI());

        return newB;

    }

    /**
     *
     * @param newGameEvent
     * @return
     */
    public static GameEvent newGameEventToGameEvent(NewGameEvent newGameEvent) {

        GameEvent newG = new GameEvent();

        newG.setAppUserId(newGameEvent.getAppUserId());
        newG.setEventType(newGameEvent.getEventType());

        return newG;
    }

    /**
     *
     * @param pointScale
     * @return
     */
    public static PointScaleToClient pointScaleToPointScaleToClient(PointScale pointScale) {

        return new PointScaleToClient().id(pointScale.getId())
                .name(pointScale.getName())
                .description(pointScale.getDescription())
                .unit(pointScale.getUnit());

    }

    /**
     *
     * @param pointScales
     * @return
     */
    public static List<PointScaleToClient> pointScaleListToPointScaleToClientList(List<PointScale> pointScales) {

        List<PointScaleToClient> pointScalesToClient = new LinkedList<>();

        if (pointScales != null) {

            for (PointScale p : pointScales) {

                PointScaleToClient tmp = new PointScaleToClient().id(p.getId())
                        .name(p.getName())
                        .description(p.getDescription())
                        .unit(p.getUnit());

                pointScalesToClient.add(tmp);
            }
        }

        return pointScalesToClient;

    }
    
    /**
     *
     * @param currentPoints
     * @return
     */
    public static List<CurrentPointsToClient> currentPointsMapToCurrentPointsList(Map<String,CurrentPoints> currentPoints) {

        List<CurrentPointsToClient> currentPointsToClient = new LinkedList<>();

        if (currentPoints != null) {

            currentPoints.forEach((k,cp) -> {
                
                        CurrentPointsToClient tmp = 
                                new CurrentPointsToClient().pointScaleName(k).currentValue(cp.getCurrentValue());

                        currentPointsToClient.add(tmp);
            });
            
        }

        return currentPointsToClient;

    }

    /**
     *
     * @param newPointScale
     * @return
     */
    public static PointScale newPointScaleToPointScale(NewPointScale newPointScale) {

        PointScale newP = new PointScale();
        newP.setName(newPointScale.getName());
        newP.setDescription(newPointScale.getDescription());
        newP.setUnit(newPointScale.getUnit());

        return newP;

    }

    /**
     *
     * @param newApp
     * @return
     */
    public static GamifiedApplication newGamifiedApplicationToGamifiedApplication(NewGamifiedApplication newApp) {

        GamifiedApplication newA = new GamifiedApplication();
        newA.setName(newApp.getName());

        UUID newToken = UUID.randomUUID();
        newA.setAuthToken(newToken.toString());

        newA.setPasswordHash(DigestUtils.md5DigestAsHex(newApp.getPassword().getBytes()));

        return newA;
    }

    /**
     *
     * @param appUser
     * @return
     */
    public static ApplicationUserToClient applicationUserToApplicationUserToClient(ApplicationUser appUser) {

        ApplicationUserToClient appUserToClient = new ApplicationUserToClient();

        appUserToClient.setApplicationName(appUser.getApplication().getName());
        appUserToClient.setAwardedBadges(badgeMapToBadgeToClientList(appUser.getAwardedBadges()));
        appUserToClient.setCurrentPoints(currentPointsMapToCurrentPointsList(appUser.getCurrentPointsList()));
        appUserToClient.setId(appUser.getId());
        appUserToClient.setIdInApplication(appUser.getIdInGamifiedApplication());
        appUserToClient.setNbEvents(appUser.getNbEvents());

        return appUserToClient;

    }
    
    public static List<ApplicationUserToClient> applicationUserListToApplicationUserToClientList(List<ApplicationUser> users){
        
        List<ApplicationUserToClient> usersToClient = new LinkedList<>();
        
        if(users != null){
            
            for(ApplicationUser appUser : users){
                
                 ApplicationUserToClient appUserToClient = new ApplicationUserToClient();

                appUserToClient.setApplicationName(appUser.getApplication().getName());
                appUserToClient.setAwardedBadges(badgeMapToBadgeToClientList(appUser.getAwardedBadges()));
                appUserToClient.setCurrentPoints(currentPointsMapToCurrentPointsList(appUser.getCurrentPointsList()));
                appUserToClient.setId(appUser.getId());
                appUserToClient.setIdInApplication(appUser.getIdInGamifiedApplication());
                appUserToClient.setNbEvents(appUser.getNbEvents());
                
                usersToClient.add(appUserToClient);
                
            }
            
        }
        
        return usersToClient;
    }

    

}
