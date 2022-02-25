package ca.jrvs.apps.twitter.service;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  /**
   * Validate and post a user input Tweet
   *
   * @param tweet tweet to be created
   * @return created tweet
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long
   *                                  out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }

  private void validatePostTweet(Tweet tweet) {
    //validate text length
    String text = tweet.getText();
    if (text.length() > 140) {
      throw new RuntimeException("Tweet length exceeds 140 characters");
    }

    //validate lat/lon values
    double lon = tweet.getCoordinates().getCoordinates()[0];
    double lat = tweet.getCoordinates().getCoordinates()[1];
    if (lon > 90 || lon < -90) {
      throw new RuntimeException("Tweet lon invalid");
    }
    if (lat > 180 || lat < -180) {
      throw new RuntimeException("Tweet lat is invalid");
    }
  }

  /**
   * Search a tweet by ID
   *
   * @param id     tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  @Override
  public Tweet showTweet(String id, String[] fields) {
    Tweet tweet = (Tweet) dao.findById(id);
    if (fields != null) {
      tweet = filterFields(tweet, fields);
    }
    return tweet;
  }

  private Tweet filterFields(Tweet tweet, String[] fields) {
    Class tweetClass = Tweet.class;
    Field fieldList[] = tweetClass.getDeclaredFields();
    Tweet filteredTweet = tweet;

    //use hashset for easy lookup of included fields
    HashSet<String> fieldsToAdd = new HashSet<>();
    for (String field : fields) {
      fieldsToAdd.add(field);
    }

    try {
      for (Field field : fieldList) {
        if (!fieldsToAdd.contains(field.getName())) { //if field not specified set to null
          //get and format field name to access set method
          char[] getAccess = field.getName().toCharArray();
          getAccess[0] = Character.toUpperCase(getAccess[0]);
          String setMethodName = "set" + String.valueOf(getAccess);

          //find and invoke set method with null value
          Method set = tweetClass.getMethod(setMethodName, field.getType());
          Object value = null;  //have to use object to set value to null or exception is thrown
          set.invoke(filteredTweet, value);
        }
      }
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException("Problem filtering tweet object according to args", e);
    }
    return filteredTweet;
  }

  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which will be deleted
   * @return A list of Tweets
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> tweets = new ArrayList<>();

    for (String id : ids) {
      try {
        Tweet deletedTweet = (Tweet) dao.deleteById(id);
        tweets.add(deletedTweet);
      } catch (RuntimeException e) {
        System.out.println("Unable to find tweet of id" + id);
      }
    }
    return tweets;
  }
}
