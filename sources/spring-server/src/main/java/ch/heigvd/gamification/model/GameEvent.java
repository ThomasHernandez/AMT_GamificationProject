package ch.heigvd.gamification.model;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class GameEvent   {
    
    @Id
    private String appUserId;

    private String eventType;

    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    

}

