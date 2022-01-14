package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class compareMapsTest {

  @Test
  public void testCompareMapsJavaAPI(){
    Map<Integer, String> m1 = new HashMap<>();
    m1.put(1, "value data");

    Map<Integer, String> m2 = new HashMap<>();
    m2.put(1, "value data");

    Map<Integer, String> m3 = new HashMap<>();
    m3.put(1, "other data");

    Assert.assertTrue(compareMaps.compareMapsJavaAPI(m1, m2));
    Assert.assertFalse(compareMaps.compareMapsJavaAPI(m2, m3));
  }

}
