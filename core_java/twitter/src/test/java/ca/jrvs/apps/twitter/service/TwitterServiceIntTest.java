package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.TweetUtils;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TwitterServiceIntTest {
  private static String CONSUMER_KEY = System.getenv("consumerKey");
  private static String CONSUMER_SECRET = System.getenv("consumerSecret");
  private static String ACCESS_TOKEN = System.getenv("accessToken");
  private static String TOKEN_SECRET = System.getenv("tokenSecret");

  TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
  TwitterDao dao = new TwitterDao(helper);

  @Test
  public void testShowTweet(){
    TwitterService service = new TwitterService(dao);
    String[] fields = {"place", "id_str", "text"};
    Tweet filteredTweet = service.showTweet("1485674992738181128", fields);
    System.out.println(filteredTweet.toString());
  }

  @Test
  public void testDeleteTweet() {
    TwitterService service = new TwitterService(dao);
    String[] ids = new String[] {"1485674992738181128"};
    List<Tweet> deletedTweets= service.deleteTweets(ids);
    Assert.assertEquals(new ArrayList<Tweet>(), deletedTweets);
  }

  @Test
  public void testCreateTweet() {
    TwitterService service = new TwitterService(dao);
    String text = "testing post tweet";
    Tweet tweet = TweetUtils.buildTweet(text, -1, 1);

    Tweet createdTweet = service.postTweet(tweet);
    Assert.assertEquals(tweet.getText(), createdTweet.getText());
  }

}
