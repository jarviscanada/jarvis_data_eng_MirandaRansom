package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.TweetUtils;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller{
  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @param args
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException();
    }
    String text = args[1];
    String[] coords = args[2].split(COORD_SEP, 2);
    double lon = Double.parseDouble(coords[0]);
    double lat = Double.parseDouble(coords[1]);

    Tweet tweet = TweetUtils.buildTweet(text, lon, lat);
    return service.postTweet(tweet);
  }

  /**
   * Parse user argument and search a tweet by calling service classes
   *
   * @param args
   * @return a tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet showTweet(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException();
    }
    String tweetId = args[1];

    String[] fields;
    if (args.length == 3) {
      fields = args[2].split(COMMA);
    } else {
      fields = null;
    }

    return service.showTweet(tweetId, fields);
  }

  /**
   * Parse user argument and delete tweets by calling service classes
   *
   * @param args
   * @return a list of deleted tweets
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException();
    }

    String[] deleteIds = args[1].split(COMMA);
    return service.deleteTweets(deleteIds);
  }
}
