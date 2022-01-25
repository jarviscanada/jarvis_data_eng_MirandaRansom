package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonUtils;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  private TwitterDao dao;

  @Before
  public void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    //dependency
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    this.dao = new TwitterDao(httpHelper);
  }

  @Test
  public void TestCreateInt() throws Exception {
    String hashtag = "#TwitterAPI";
    String text = "Aries has some pretty good bangers huh?" + hashtag;
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {-1, 1});

    Tweet postTweet = new Tweet();
    postTweet.setText(text);
    postTweet.setCoordinates(coordinates);

    System.out.println(JsonUtils.toJson(postTweet, true, false));
    Tweet tweet = dao.create(postTweet);

    Assert.assertEquals(text, tweet.getText());
    Assert.assertTrue(hashtag.contains(tweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void TestLookupInt() {
    String id = "1486049300203380740";
    String expectedText = "five cups of green tea with honey";

    Tweet tweet = dao.findById(id);
    Assert.assertEquals(expectedText, tweet.getText());
    Assert.assertEquals(id, tweet.getId_str());
  }

  @Test
  public void TestDelete() {
    Tweet post = new Tweet();
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(new double[] {-1, 1});

    post.setText("testing delete functionality");
    post.setCoordinates(coordinates);
    Tweet postedTweet = dao.create(post);
    String id = postedTweet.getId_str();

    Tweet tweet = dao.deleteById(id);
    Assert.assertEquals(id, tweet.getId_str());
  }

}
