package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TwitterControllerIntTest {
  private static String CONSUMER_KEY = System.getenv("consumerKey");
  private static String CONSUMER_SECRET = System.getenv("consumerSecret");
  private static String ACCESS_TOKEN = System.getenv("accessToken");
  private static String TOKEN_SECRET = System.getenv("tokenSecret");

  TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
  TwitterDao dao = new TwitterDao(helper);
  TwitterService service = new TwitterService(dao);

  @Test
  public void testPostTweet() {
    TwitterController controller = new TwitterController(service);
    String[] args = new String[] {"post", "today it's mint tea with lime. review: it's good", "5:-10"};
    Tweet tweet = controller.postTweet(args);

    Assert.assertNotNull(tweet);
    Assert.assertEquals(tweet.getText(), args[1]);
    Assert.assertEquals(tweet.getCoordinates().getLon(), 5, 0);
    Assert.assertEquals(tweet.getCoordinates().getLat(), -10, 0);
  }

  @Test
  public void testShowTweet() {
    TwitterController controller = new TwitterController(service);
    String expectedText = "today it's mint tea with lime. review: it's good";
    String[] args = new String[] {"show", "1486459200192589846"};
    Tweet tweet = controller.showTweet(args);

    Assert.assertNotNull(tweet);
    Assert.assertEquals(tweet.getText(), expectedText);
  }

  @Test
  public void testDeleteTweet() {
    TwitterController controller = new TwitterController(service);
    String expectedText;
    String[] args = new String[] {"delete", "1484260632006438914"};
    List<Tweet> tweets = controller.deleteTweet(args);

    Assert.assertNotNull(tweets);
  }

}
