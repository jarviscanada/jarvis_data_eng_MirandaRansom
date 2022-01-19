package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * Ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-187f9bc323f14195949c94bed7a7eb45
 */
public class CompareMaps {
  /**
   *  Problem: compare 2 maps in Java
   *
   *  Considerations:
   *  consider custom class?
   *  is Java API allowed?
   *  compare reference or value equals
   */

  public static  <K, V> boolean compareMapsJavaAPI(Map<K,V> m1, Map<K,V> m2){
    return m1.equals(m2);  //compares both keys and values (both must have proper equals implementations)
  }
}
