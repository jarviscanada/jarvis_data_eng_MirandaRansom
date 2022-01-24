package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonUtils;
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
    String text = "@mkr0129 has been wanting to travel overseas " + hashtag;

    Tweet postTweet = new Tweet();
    postTweet.setText(text);

    System.out.println(JsonUtils.toJson(postTweet, true, false));
    Tweet tweet = dao.create(postTweet);

    Assert.assertEquals(text, tweet.getText());
    Assert.assertTrue(hashtag.contains(tweet.getEntities().getHashtags().get(0).getText()));
  }
}
