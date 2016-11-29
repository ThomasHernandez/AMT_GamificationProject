package ch.heigvd.gamification.model;

import ch.heigvd.gamification.api.dto.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * GameEvent
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-29T14:22:08.299+01:00")

public class GameEvent   {
  private Long appId = null;

  private String data = null;

  public GameEvent appId(Long appId) {
    this.appId = appId;
    return this;
  }

   /**
   * Get appId
   * @return appId
  **/
  @ApiModelProperty(required = true, value = "")
  public Long getAppId() {
    return appId;
  }

  public void setAppId(Long appId) {
    this.appId = appId;
  }

  public GameEvent data(String data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(required = true, value = "")
  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GameEvent gameEvent = (GameEvent) o;
    return Objects.equals(this.appId, gameEvent.appId) &&
        Objects.equals(this.data, gameEvent.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appId, data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GameEvent {\n");
    
    sb.append("    appId: ").append(toIndentedString(appId)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

