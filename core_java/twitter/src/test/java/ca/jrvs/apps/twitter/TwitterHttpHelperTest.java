package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterHttpHelperTest {
  private static String CONSUMER_KEY = System.getenv("consumerKey");
  private static String CONSUMER_SECRET = System.getenv("consumerSecret");
  private static String ACCESS_TOKEN = System.getenv("accessToken");
  private static String TOKEN_SECRET = System.getenv("accessSecret");

  TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
  final Logger logger = LoggerFactory.getLogger(TwitterHttpHelperTest.class);

  @Test
  public void testGet() throws Exception {
    HttpResponse response =  helper.httpGet(
        URI.create("https://api.twitter.com/1.1/search/tweets.json?q=1483176991985704967"));
    logger.info("GET response: {}", EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void testPost() throws Exception {
    HttpResponse response = helper.httpPost(
        URI.create("https://api.twitter.com/1.1/statuses/update.json?status=HelloWorld"));
    logger.info("POST response: {}", EntityUtils.toString(response.getEntity()));
  }
}
