package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * NewPointScale
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-29T09:54:16.483Z")

public class NewPointScale   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("lowerBound")
  private Double lowerBound = null;

  @JsonProperty("upperBound")
  private Double upperBound = null;

  @JsonProperty("isIntegerScale")
  private Boolean isIntegerScale = null;

  @JsonProperty("unit")
  private String unit = null;

  public NewPointScale name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public NewPointScale description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(required = true, value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NewPointScale lowerBound(Double lowerBound) {
    this.lowerBound = lowerBound;
    return this;
  }

   /**
   * Get lowerBound
   * @return lowerBound
  **/
  @ApiModelProperty(required = true, value = "")
  public Double getLowerBound() {
    return lowerBound;
  }

  public void setLowerBound(Double lowerBound) {
    this.lowerBound = lowerBound;
  }

  public NewPointScale upperBound(Double upperBound) {
    this.upperBound = upperBound;
    return this;
  }

   /**
   * Get upperBound
   * @return upperBound
  **/
  @ApiModelProperty(required = true, value = "")
  public Double getUpperBound() {
    return upperBound;
  }

  public void setUpperBound(Double upperBound) {
    this.upperBound = upperBound;
  }

  public NewPointScale isIntegerScale(Boolean isIntegerScale) {
    this.isIntegerScale = isIntegerScale;
    return this;
  }

   /**
   * Get isIntegerScale
   * @return isIntegerScale
  **/
  @ApiModelProperty(required = true, value = "")
  public Boolean getIsIntegerScale() {
    return isIntegerScale;
  }

  public void setIsIntegerScale(Boolean isIntegerScale) {
    this.isIntegerScale = isIntegerScale;
  }

  public NewPointScale unit(String unit) {
    this.unit = unit;
    return this;
  }

   /**
   * Get unit
   * @return unit
  **/
  @ApiModelProperty(required = true, value = "")
  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewPointScale newPointScale = (NewPointScale) o;
    return Objects.equals(this.name, newPointScale.name) &&
        Objects.equals(this.description, newPointScale.description) &&
        Objects.equals(this.lowerBound, newPointScale.lowerBound) &&
        Objects.equals(this.upperBound, newPointScale.upperBound) &&
        Objects.equals(this.isIntegerScale, newPointScale.isIntegerScale) &&
        Objects.equals(this.unit, newPointScale.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, lowerBound, upperBound, isIntegerScale, unit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewPointScale {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    lowerBound: ").append(toIndentedString(lowerBound)).append("\n");
    sb.append("    upperBound: ").append(toIndentedString(upperBound)).append("\n");
    sb.append("    isIntegerScale: ").append(toIndentedString(isIntegerScale)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

