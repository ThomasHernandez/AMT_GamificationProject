package ch.heigvd.gamification.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Antony
 */
@Entity
public class GameEvent   {
    
    @Id
    private String appUserId;

    private String eventType;
    

    /**
     *
     * @return
     */
    public String getAppUserId() {
        return appUserId;
    }

    /**
     *
     * @param appUserId
     */
    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    /**
     *
     * @return
     */
    public String getEventType() {
        return eventType;
    }

    /**
     *
     * @param eventType
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    

}

