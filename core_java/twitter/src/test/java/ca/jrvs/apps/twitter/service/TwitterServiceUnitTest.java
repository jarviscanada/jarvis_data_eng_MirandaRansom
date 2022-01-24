package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;

public class TwitterServiceUnitTest {
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

}
