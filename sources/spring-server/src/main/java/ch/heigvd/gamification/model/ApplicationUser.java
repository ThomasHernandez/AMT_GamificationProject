package ch.heigvd.gamification.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Antony
 */
@Entity
public class ApplicationUser {
    
    @Id
    private Long id;
    
    private String username;
    
    @OneToMany
    private List<Badge> awardedBadges;
    
    @OneToMany
    private List<PointScale> currentPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    
    
    
}
