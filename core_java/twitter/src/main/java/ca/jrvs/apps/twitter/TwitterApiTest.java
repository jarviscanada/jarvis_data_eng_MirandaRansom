package ca.jrvs.apps.twitter;

import com.google.gdata.util.common.base.PercentEscaper;
import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class TwitterApiTest {
  private static String CONSUMER_KEY = System.getenv("consumerKey");
  private static String CONSUMER_SECRET = System.getenv("consumerSecret");
  private static String ACCESS_TOKEN = System.getenv("accessToken");
  private static String TOKEN_SECRET = System.getenv("accessSecret");

  public static void main(String[] args) throws Exception{
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

    String username = "mkr0129";
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    HttpGet request = new HttpGet(
      "https://api.twitter.com/2/users/by/username/" + percentEscaper.escape(username));

    consumer.sign(request);

    System.out.println("Http Request Headers:");
    Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpResponse response = httpClient.execute(request);
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}
