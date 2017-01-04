package ch.heigvd.gamification.model;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class GameEvent   {
    
    @Id
    private String appUserId;

    private String eventType;

    private String pointScaleToUpdateName;
    
    private double newPoints;

    public String getPointScaleToUpdate() {
        return pointScaleToUpdateName;
    }

    public void setPointScaleToUpdate(String pointScaleToUpdateName) {
        this.pointScaleToUpdateName = pointScaleToUpdateName;
    }

    public double getNewPoints() {
        return newPoints;
    }

    public void setNewPoints(double newPoints) {
        this.newPoints = newPoints;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    

}

