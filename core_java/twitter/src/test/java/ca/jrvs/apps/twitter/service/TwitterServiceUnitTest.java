package ca.jrvs.apps.twitter.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.TweetUtils;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  @Test
  public void testPostTweet() {
    when(dao.create(any())).thenReturn(new Tweet());
    service.postTweet(TweetUtils.buildTweet("test", -1, 1));
  }

  @Test
  public void testShowTweet() {
    when(dao.findById(any())).thenReturn(new Tweet());
    service.showTweet(any(), new String[] {"text"});
  }

  @Test
  public void testDeleteTweet() {
    when(dao.deleteById(any())).thenReturn(new Tweet());
    service.deleteTweets(new String[] {"123456"});
  }
}
