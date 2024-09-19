package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Hashtag {
  public int[] indices;
  public String text;

  public int[] getIndices() {
    return indices;
  }

  public void setIndices(int[] indices) {
    this.indices = indices;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
