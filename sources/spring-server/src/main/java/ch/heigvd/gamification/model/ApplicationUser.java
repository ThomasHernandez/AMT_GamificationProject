package ch.heigvd.gamification.model;

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
public class ApplicationUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private GamifiedApplication application;
    
    private String idInGamifiedApplication;
    
    @OneToMany
    private Map<String,Badge> awardedBadges;
    
    @OneToMany
    private Map<String,PointScale> currentPoints;
    
    private int nbEvents;

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
    public Map<String,Badge> getAwardedBadges() {
        return awardedBadges;
    }

    /**
     *
     * @param awardedBadges
     */
    public void setAwardedBadges(Map<String,Badge> awardedBadges) {
        this.awardedBadges = awardedBadges;
    }

    /**
     *
     * @return
     */
    public Map<String,PointScale> getCurrentPoints() {
        return currentPoints;
    }

    /**
     *
     * @param currentPoints
     */
    public void setCurrentPoints(Map<String,PointScale> currentPoints) {
        this.currentPoints = currentPoints;
    }

    /**
     *
     * @return
     */
    public int getNbEvents() {
        return nbEvents;
    }

    /**
     *
     * @param nbEvents
     */
    public void setNbEvents(int nbEvents) {
        this.nbEvents = nbEvents;
    }

    /**
     *
     * @return
     */
    public String getIdInGamifiedApplication() {
        return idInGamifiedApplication;
    }

    /**
     *
     * @param idInGamifiedApplication
     */
    public void setIdInGamifiedApplication(String idInGamifiedApplication) {
        this.idInGamifiedApplication = idInGamifiedApplication;
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
    
    
    
    
}
