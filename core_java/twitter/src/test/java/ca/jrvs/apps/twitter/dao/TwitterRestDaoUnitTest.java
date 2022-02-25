package ca.jrvs.apps.twitter.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.TweetUtils;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.JsonUtils;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterRestDaoUnitTest {
  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  @Test
  public void TestCreateTweet() throws Exception {
    //failed request
    String hashtag = "#TwitterAPI";
    String text = "@mkr0129 learning Mockito and Unit testing " + hashtag;
    Double lat = 1d;
    Double lon = -1d;
    try {
      Tweet postTweet = TweetUtils.buildTweet(text, lon, lat);
      dao.create(postTweet);
    } catch (RuntimeException e) {
      Assert.assertTrue(true);
    }

    //test happy path
    String tweetJsonStr = "{\n"
        + " \"created_at\" : \"Fri Jan 21 03:27:20 +0000 2022\",\n"
        + "  \"id\" : 1484366976914915336,\n"
        + "  \"id_str\" : \"1484366976914915336\",\n"
        + "  \"text\" : \"wow.  incredible must watch video from @hankgreen i hope the folks at @tiktok_us are watching.\",\n"
        + "  \"entities\" : {\n"
        + "    \"hashtags\" : [ ],\n"
        + "    \"user_mentions\" : [ {\n"
        + "      \"id\" : 61592079,\n"
        + "      \"id_str\" : \"61592079\",\n"
        + "      \"indices\" : [ 39, 49 ],\n"
        + "      \"name\" : \"Hank Green\",\n"
        + "      \"screen_name\" : \"hankgreen\"\n"
        + "    }, {\n"
        + "      \"id\" : 2401980110,\n"
        + "      \"id_str\" : \"2401980110\",\n"
        + "      \"indices\" : [ 70, 80 ],\n"
        + "      \"name\" : \"TikTok\",\n"
        + "      \"screen_name\" : \"tiktok_us\"\n"
        + "    } ]\n"
        + "  },\n"
        + "  \"retweet_count\" : 22,\n"
        + "  \"favorite_count\" : 1073,\n"
        + "  \"favorited\" : true,\n"
        + "  \"retweeted\" : false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtils.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parse response body -- spyDao to fake parseResponseBody
    doReturn(expectedTweet).when(spyDao).processResponseToTweet(any(), anyInt());

    Tweet postTweet = new Tweet();
    Coordinates cord = new Coordinates();
    cord.setCoordinates(new double[] {lon, lat});
    postTweet.setCoordinates(cord);
    postTweet.setText(text);

    Tweet tweet = spyDao.create(postTweet);
    Assert.assertNotNull(tweet);
    Assert.assertNotNull(tweet.getText());
  }

  @Test
  public void TestDeleteTweet() throws Exception {
    //failed request
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.deleteById("1484366976914915336");
    } catch (RuntimeException e) {
      Assert.assertTrue(true);
    }

    //happy request
    String tweetJsonStr = "{\n"
        + " \"created_at\" : \"Fri Jan 21 03:27:20 +0000 2022\",\n"
        + "  \"id\" : 1484366976914915336,\n"
        + "  \"id_str\" : \"1484366976914915336\",\n"
        + "  \"text\" : \"wow.  incredible must watch video from @hankgreen i hope the folks at @tiktok_us are watching.\",\n"
        + "  \"entities\" : {\n"
        + "    \"hashtags\" : [ ],\n"
        + "    \"user_mentions\" : [ {\n"
        + "      \"id\" : 61592079,\n"
        + "      \"id_str\" : \"61592079\",\n"
        + "      \"indices\" : [ 39, 49 ],\n"
        + "      \"name\" : \"Hank Green\",\n"
        + "      \"screen_name\" : \"hankgreen\"\n"
        + "    }, {\n"
        + "      \"id\" : 2401980110,\n"
        + "      \"id_str\" : \"2401980110\",\n"
        + "      \"indices\" : [ 70, 80 ],\n"
        + "      \"name\" : \"TikTok\",\n"
        + "      \"screen_name\" : \"tiktok_us\"\n"
        + "    } ]\n"
        + "  },\n"
        + "  \"retweet_count\" : 22,\n"
        + "  \"favorite_count\" : 1073,\n"
        + "  \"favorited\" : true,\n"
        + "  \"retweeted\" : false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtils.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).processResponseToTweet(any(), anyInt());

    Tweet tweet = spyDao.deleteById("1484366976914915336");
    Assert.assertNotNull(tweet);
    Assert.assertNotNull(tweet.getText());
  }

  @Test
  public void TestShowTweet() throws Exception {
    //failed request
    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.findById("1484366976914915336");
    } catch (RuntimeException e) {
      Assert.assertTrue(true);
    }

    //happy path
    String tweetJsonStr = "{\n"
        + " \"created_at\" : \"Fri Jan 21 03:27:20 +0000 2022\",\n"
        + "  \"id\" : 1484366976914915336,\n"
        + "  \"id_str\" : \"1484366976914915336\",\n"
        + "  \"text\" : \"wow.  incredible must watch video from @hankgreen i hope the folks at @tiktok_us are watching.\",\n"
        + "  \"entities\" : {\n"
        + "    \"hashtags\" : [ ],\n"
        + "    \"user_mentions\" : [ {\n"
        + "      \"id\" : 61592079,\n"
        + "      \"id_str\" : \"61592079\",\n"
        + "      \"indices\" : [ 39, 49 ],\n"
        + "      \"name\" : \"Hank Green\",\n"
        + "      \"screen_name\" : \"hankgreen\"\n"
        + "    }, {\n"
        + "      \"id\" : 2401980110,\n"
        + "      \"id_str\" : \"2401980110\",\n"
        + "      \"indices\" : [ 70, 80 ],\n"
        + "      \"name\" : \"TikTok\",\n"
        + "      \"screen_name\" : \"tiktok_us\"\n"
        + "    } ]\n"
        + "  },\n"
        + "  \"retweet_count\" : 22,\n"
        + "  \"favorite_count\" : 1073,\n"
        + "  \"favorited\" : true,\n"
        + "  \"retweeted\" : false\n"
        + "}";

    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtils.toObjectFromJson(tweetJsonStr, Tweet.class);
    doReturn(expectedTweet).when(spyDao).processResponseToTweet(any(), anyInt());

    Tweet tweet = spyDao.findById("1484366976914915336");
    Assert.assertNotNull(tweet);
    Assert.assertNotNull(tweet.getText());
  }

}
