package ch.heigvd.gamification.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Antony
 */



@Entity
public class ApplicationUser implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private GamifiedApplication application;
    
    private String idInGamifiedApplication;
    
    @OneToMany
    private Map<String, CurrentPoints> currentPointsList;
    
    @OneToMany
    private Map<String, BadgeAward> awardedBadges;
    
    private int nbEvents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GamifiedApplication getApplication() {
        return application;
    }

    public void setApplication(GamifiedApplication application) {
        this.application = application;
    }

    public String getIdInGamifiedApplication() {
        return idInGamifiedApplication;
    }

    public void setIdInGamifiedApplication(String idInGamifiedApplication) {
        this.idInGamifiedApplication = idInGamifiedApplication;
    }

    public Map<String, CurrentPoints> getCurrentPointsList() {
        return currentPointsList;
    }

    public void setCurrentPointsList(Map<String, CurrentPoints> currentPointsList) {
        this.currentPointsList = currentPointsList;
    }

    public Map<String, BadgeAward> getAwardedBadges() {
        return awardedBadges;
    }

    public void setAwardedBadges(Map<String, BadgeAward> awardedBadges) {
        this.awardedBadges = awardedBadges;
    }

    public int getNbEvents() {
        return nbEvents;
    }

    public void setNbEvents(int nbEvents) {
        this.nbEvents = nbEvents;
    }

    
    
}
