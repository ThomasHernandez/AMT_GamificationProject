package ch.heigvd.gamification.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Antony
 */
@Entity
public class GamifiedApplication implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String passwordHash;
    private String authToken;
    
    @OneToMany
    private List<Badge> applicationBadges;
    
    @OneToMany
    private List<Rule> applicationRules;
    
    @OneToMany
    private List<PointScale> applicationPointScales;
    
    @OneToMany
    private List<ApplicationUser> applicationUsers;

    

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
    public String getAuthToken() {
        return authToken;
    }

    /**
     *
     * @param authToken
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     *
     * @return
     */
    public List<Badge> getApplicationBadges() {
        return applicationBadges;
    }

    /**
     *
     * @param applicationBadges
     */
    public void setApplicationBadges(List<Badge> applicationBadges) {
        this.applicationBadges = applicationBadges;
    }

    /**
     *
     * @return
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     *
     * @param passwordHash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     *
     * @return
     */
    public List<Rule> getApplicationRules() {
        return applicationRules;
    }

    /**
     *
     * @param applicationRules
     */
    public void setApplicationRules(List<Rule> applicationRules) {
        this.applicationRules = applicationRules;
    }

    /**
     *
     * @return
     */
    public List<PointScale> getApplicationPointScales() {
        return applicationPointScales;
    }

    /**
     *
     * @param applicationPointScales
     */
    public void setApplicationPointScales(List<PointScale> applicationPointScales) {
        this.applicationPointScales = applicationPointScales;
    }
    
    /**
     *
     * @return
     */
    public List<ApplicationUser> getApplicationUsers() {
        return applicationUsers;
    }

    /**
     *
     * @param applicationUsers
     */
    public void setApplicationUsers(List<ApplicationUser> applicationUsers) {
        this.applicationUsers = applicationUsers;
    }
    
}
