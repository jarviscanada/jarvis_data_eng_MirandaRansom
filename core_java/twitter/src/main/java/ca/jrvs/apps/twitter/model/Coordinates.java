package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {
  private double lat;
  private double lon;

  @JsonProperty("coordinates")
  private double[] coordinates;

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLon() {
    return lon;
  }

  public void setLon(double lon) {
    this.lon = lon;
  }

  public double[] getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(double[] coordinates) {
    this.coordinates = coordinates;
  }
}
