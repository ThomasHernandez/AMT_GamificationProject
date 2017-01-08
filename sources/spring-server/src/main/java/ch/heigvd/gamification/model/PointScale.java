package ch.heigvd.gamification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Antony
 */
@Entity
public class PointScale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Double lowerBound;

    private Double upperBound;

    private Boolean isIntegerScale;

    private String unit;
    
    private Double currentValue;

    /**
     *
     * @param currentValue
     */
    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

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
    public Double getLowerBound() {
        return lowerBound;
    }

    /**
     *
     * @param lowerBound
     */
    public void setLowerBound(Double lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     *
     * @return
     */
    public Double getUpperBound() {
        return upperBound;
    }

    /**
     *
     * @param upperBound
     */
    public void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    /**
     *
     * @return
     */
    public Boolean getIsIntegerScale() {
        return isIntegerScale;
    }

    /**
     *
     * @param isIntegerScale
     */
    public void setIsIntegerScale(Boolean isIntegerScale) {
        this.isIntegerScale = isIntegerScale;
    }

    /**
     *
     * @return
     */
    public String getUnit() {
        return unit;
    }

    /**
     *
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    /**
     *
     * @return
     */
    public Double getCurrentValue() {
        return currentValue;
    }
    
}
