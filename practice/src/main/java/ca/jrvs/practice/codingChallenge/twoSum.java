package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/Two-Sum-4623734546204613ba8acad8795d4d6b
 */
public class twoSum {

  public static int[] twoSumNaive(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if (arr[i] + arr[j] == target) {
          return new int[]{i, j};
        }
      }
    }
    return new int[]{-1, -1};
  }

  public static int[] twoSumLinear(int[] arr, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      int compliment = target - arr[i];
      if (map.containsKey(compliment)) {
        return new int[]{map.get(compliment), i};
      }
      map.put(arr[i], i);
    }
    return null;  //no solution
  }
}
