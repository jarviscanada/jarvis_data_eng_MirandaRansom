package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;

public class TweetUtils {
  public static Tweet buildTweet(String text, double lon, double lat) {
    Tweet tweet = new Tweet();
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {lon, lat});
    tweet.setCoordinates(coordinates);
    tweet.setText(text);
    return tweet;
  }

}
