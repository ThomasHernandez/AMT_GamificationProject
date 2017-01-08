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

    private String pointScaleToUpdateName;
    
    private double newPoints;

    /**
     *
     * @return
     */
    public String getPointScaleToUpdate() {
        return pointScaleToUpdateName;
    }

    /**
     *
     * @param pointScaleToUpdateName
     */
    public void setPointScaleToUpdate(String pointScaleToUpdateName) {
        this.pointScaleToUpdateName = pointScaleToUpdateName;
    }

    /**
     *
     * @return
     */
    public double getNewPoints() {
        return newPoints;
    }

    /**
     *
     * @param newPoints
     */
    public void setNewPoints(double newPoints) {
        this.newPoints = newPoints;
    }

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

