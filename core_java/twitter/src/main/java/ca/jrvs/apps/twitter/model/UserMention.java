package ca.jrvs.apps.twitter.model;

import java.util.Arrays;

public class UserMention {
  public long id;
  public String id_str;
  public int[] indices;
  public String name;
  public String screen_name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getId_str() {
    return id_str;
  }

  public void setId_str(String id_str) {
    this.id_str = id_str;
  }

  public int[] getIndices() {
    return indices;
  }

  public void setIndices(int[] indices) {
    this.indices = indices;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getScreen_name() {
    return screen_name;
  }

  public void setScreen_name(String screen_name) {
    this.screen_name = screen_name;
  }

  @Override
  public String toString() {
    return "UserMention{" +
        "id=" + id +
        ", id_str='" + id_str + '\'' +
        ", indices=" + Arrays.toString(indices) +
        ", name='" + name + '\'' +
        ", screen_name='" + screen_name + '\'' +
        '}';
  }
}
