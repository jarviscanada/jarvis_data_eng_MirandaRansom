package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.TweetUtils;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.JsonUtils;
import ca.jrvs.apps.twitter.model.Tweet;
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
    Tweet postTweet = TweetUtils.buildTweet(text, -1, 1);


    System.out.println(JsonUtils.toJson(postTweet, true, false));
    Tweet tweet = dao.create(postTweet);

    Assert.assertEquals(text, tweet.getText());
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
    String text = "testing delete functionality";
    Tweet post = TweetUtils.buildTweet(text, -1, 1);

    Tweet postedTweet = dao.create(post);
    String id = postedTweet.getId_str();

    Tweet tweet = dao.deleteById(id);
    Assert.assertEquals(id, tweet.getId_str());
  }

}
