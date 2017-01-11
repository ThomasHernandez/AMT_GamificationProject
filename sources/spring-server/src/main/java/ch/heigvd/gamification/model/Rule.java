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
    
    private double pointsToAdd;
    
    @ManyToOne
    private Badge badgeToAward;
    
    private double valueToReach;

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     *
     * @return
     */
    public GamifiedApplication getApplication() {
        return application;
    }

    /**
     *
     * @param application
     */
    public void setApplication(GamifiedApplication application) {
        this.application = application;
    }

    /**
     *
     * @return
     */
    public PointScale getPointScaleToCheck() {
        return pointScaleToCheck;
    }

    /**
     *
     * @param pointScaleToCheck
     */
    public void setPointScaleToCheck(PointScale pointScaleToCheck) {
        this.pointScaleToCheck = pointScaleToCheck;
    }

    /**
     *
     * @return
     */
    public Badge getBadgeToAward() {
        return badgeToAward;
    }

    /**
     *
     * @param badgeToAward
     */
    public void setBadgeToAward(Badge badgeToAward) {
        this.badgeToAward = badgeToAward;
    }

    /**
     *
     * @return
     */
    public double getValueToReach() {
        return valueToReach;
    }

    /**
     *
     * @param valueToReach
     */
    public void setValueToReach(double valueToReach) {
        this.valueToReach = valueToReach;
    }

    /**
     *
     * @return
     */
    public double getPointsToAdd() {
        return pointsToAdd;
    }

    /**
     *
     * @param pointsToAdd
     */
    public void setPointsToAdd(double pointsToAdd) {
        this.pointsToAdd = pointsToAdd;
    }
    
    
    
    
    
    
}
