package ch.heigvd.gamification.model;

import java.util.List;
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
public class ApplicationUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private GamifiedApplication application;
    
    private String idInGamifiedApplication;
    
    @OneToMany
    private List<Badge> awardedBadges;
    
    @OneToMany
    private List<PointScale> currentPoints;
    
    private int nbEvents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Badge> getAwardedBadges() {
        return awardedBadges;
    }

    public void setAwardedBadges(List<Badge> awardedBadges) {
        this.awardedBadges = awardedBadges;
    }

    public List<PointScale> getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(List<PointScale> currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getNbEvents() {
        return nbEvents;
    }

    public void setNbEvents(int nbEvents) {
        this.nbEvents = nbEvents;
    }

    public String getIdInGamifiedApplication() {
        return idInGamifiedApplication;
    }

    public void setIdInGamifiedApplication(String idInGamifiedApplication) {
        this.idInGamifiedApplication = idInGamifiedApplication;
    }

    public GamifiedApplication getApplication() {
        return application;
    }

    public void setApplication(GamifiedApplication application) {
        this.application = application;
    }
    
    
    
    
}
