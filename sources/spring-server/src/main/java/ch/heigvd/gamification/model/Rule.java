/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Antony
 */
@Entity
public class Rule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String description;
    private String eventType;
    
    @ManyToOne
    private GamifiedApplication application;
    
    @ManyToOne
    private PointScale pointScaleToCheck;
    
    @ManyToOne
    private Badge badgeToAward;
    
    double valueToReach;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public GamifiedApplication getApplication() {
        return application;
    }

    public void setApplication(GamifiedApplication application) {
        this.application = application;
    }

    public PointScale getPointScaleToCheck() {
        return pointScaleToCheck;
    }

    public void setPointScaleToCheck(PointScale pointScaleToCheck) {
        this.pointScaleToCheck = pointScaleToCheck;
    }

    public Badge getBadgeToAward() {
        return badgeToAward;
    }

    public void setBadgeToAward(Badge badgeToAward) {
        this.badgeToAward = badgeToAward;
    }

    public double getValueToReach() {
        return valueToReach;
    }

    public void setValueToReach(double valueToReach) {
        this.valueToReach = valueToReach;
    }
    
    
    
    
    
    
}
