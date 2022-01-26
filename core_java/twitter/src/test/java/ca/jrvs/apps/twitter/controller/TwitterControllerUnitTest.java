package ca.jrvs.apps.twitter.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  TwitterService service;

  @InjectMocks
  TwitterController controller;

  @Test
  public void testPostTweet() throws RuntimeException{
    when(service.postTweet(any())).thenReturn(new Tweet());
    //fail path
    String[] argsFail = new String[] {"post"};
    try {
      controller.postTweet(argsFail);
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }

    //happy path
    String[] argsPass = new String[] {"post", "test post", "-1:1"};
    Tweet tweet = controller.postTweet(argsPass);
    Assert.assertNotNull(tweet);
  }

  @Test
  public void testShowTweet() {
    when(service.showTweet(anyString(), any())).thenReturn(new Tweet());

    //fail path
    String[] argsFail = new String[] {""};
    try {
      controller.showTweet(argsFail);
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }

    //happy path
    String[] argsPass = new String[] {"show", "12345432"};
    Tweet tweet = controller.showTweet(argsPass);
    Assert.assertNotNull(tweet);
  }

  @Test
  public void testDeleteTweet() {
    when(service.deleteTweets(any())).thenReturn(new ArrayList<Tweet>());

    //fail path
    String[] argsFail = new String[] {"delete"};
    try {
      service.deleteTweets(argsFail);
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }

    //happy path
    String[] argsPass = new String[] {"delete",  "123454, 14631"};
    List<Tweet> tweets = service.deleteTweets(argsPass);
    Assert.assertNotNull(tweets);
  }

}
