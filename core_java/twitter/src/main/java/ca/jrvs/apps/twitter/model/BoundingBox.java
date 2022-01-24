package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BoundingBox {
  @JsonProperty("coordinates")
  private Coordinates coordinates;
  @JsonProperty("type")
  private String type;

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public class Coordinates {
    private List<List<Float>> coordinateList;

    public List<List<Float>> getCoordinateList() {
      return coordinateList;
    }

    public void setCoordinateList(List<List<Float>> coordinateList) {
      this.coordinateList = coordinateList;
    }
  }
}

