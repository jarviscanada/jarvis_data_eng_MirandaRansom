package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonUtils;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterDao implements CrdDao<Tweet, String> {

  private static String CONSUMER_KEY = System.getenv("consumerKey");
  private static String CONSUMER_SECRET = System.getenv("consumerSecret");
  private static String ACCESS_TOKEN = System.getenv("accessToken");
  private static String TOKEN_SECRET = System.getenv("tokenSecret");

  //URI constraints
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Expected response code
  private static final int HTTP_OK = 200;
  private HttpHelper httpHelper;

  @Autowired
  public TwitterDao (HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }


  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param entity entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet entity) {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    String text = entity.getText();
    URI uri = URI.create(API_BASE_URI + POST_PATH + QUERY_SYM + "status=" + percentEscaper.escape(text));
    HttpResponse response = httpHelper.httpPost(uri);
    return processResponseToTweet(response, HTTP_OK);
  }

  /**
   * Find an entity(Tweet) by its id
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    URI uri = URI.create(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id=" + percentEscaper.escape(s));
    HttpResponse response = httpHelper.httpGet(uri);
    return processResponseToTweet(response, HTTP_OK);
  }

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param s of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String s) {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    URI uri = URI.create(API_BASE_URI + DELETE_PATH + "/" + percentEscaper.escape(s) + ".json");
    HttpResponse response = httpHelper.httpPost(uri);
    return processResponseToTweet(response, HTTP_OK);
  }

  public Tweet processResponseToTweet(HttpResponse response, int i){
    Tweet tweet = null;
    //check status
    int status = response.getStatusLine().getStatusCode();
    if(status != HTTP_OK) {
      throw new RuntimeException("Unexpected HTTP status: " + status);
    }
    //check for empty body
    if (response.getEntity() == null) {
      throw new RuntimeException("Empty Response Body");
    }
    //process body into Tweet
    try {
      String str = EntityUtils.toString(response.getEntity());
      tweet = JsonUtils.toObjectFromJson(str, Tweet.class);
    } catch (IOException e){
      throw new RuntimeException("Unable to parse json into object", e);
    }
    return tweet;
  }

  public static void main(String[] args) throws JsonProcessingException {
    TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    final Logger logger = LoggerFactory.getLogger(TwitterDao.class);

   TwitterDao dao = new TwitterDao(helper);
   Tweet tweet = dao.findById("1484594280530423809");

   logger.info(tweet.getText());
   logger.info(JsonUtils.toJson(tweet, true, true));
   logger.info(tweet.getPlace().getLocation());
   logger.info(tweet.getEntities().getUserMentions().toString());
  }
}
