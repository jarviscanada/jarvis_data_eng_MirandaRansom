package ca.jrvs.practice.codingChallenge;

import java.security.Key;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

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

  public static  <K, V> boolean compareMapsImplementEquals(Map<K,V> m1, Map<K,V> m2){
    if (!m1.keySet().equals(m2.keySet())) {
      return false; //key mismatch
    }

    ArrayList<V> values1 = new ArrayList<>(m1.values());
    ArrayList<V> values2 = new ArrayList<>(m2.values());
    if (!values1.equals(values2)) {
      return false; //value mismatch
    }
    return true;
  }
}
