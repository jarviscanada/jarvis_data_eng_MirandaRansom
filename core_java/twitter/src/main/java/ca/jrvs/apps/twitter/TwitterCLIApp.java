package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public class TwitterCLIApp {
  public static final String USAGE = "USAGE: TwitterCLIApp post|show|delete [options]";

  private Controller controller;

  public TwitterCLIApp(Controller controller) { this.controller = controller; }

  public static void main(String[] args) {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    TwitterHttpHelper helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    TwitterDao dao = new TwitterDao(helper);
    TwitterService service = new TwitterService(dao);
    TwitterController controller = new TwitterController(service);
    TwitterCLIApp app = new TwitterCLIApp(controller);
    app.run(args);
  }

  /**
   * Called from main parses args and calls controller methods
   * @param args
   * @throws IllegalArgumentException
   */
  public void run(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException(USAGE);
    }

    String command = args[0].toLowerCase();
    switch (command) {
      case "post":
        printTweet(controller.postTweet(args));
        break;
      case "show":
        printTweet(controller.showTweet(args));
        break;
      case "delete":
        List<Tweet> tweets= controller.deleteTweet(args);
        for (Tweet tweet : tweets) {
          printTweet(tweet);
        }
        break;
      default:
        throw new IllegalArgumentException(USAGE);
    }
  }

  private void printTweet(Tweet tweet) {
    try {
      System.out.println(JsonUtils.toJson(tweet, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert tweet into json", e);
    }
  }
}
