package ca.jrvs.practice.codingChallenge;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * ticket: https://www.notion.so/jarvisdev/Find-Largest-Smallest-29c67f094dd84dcd9d2c84383c2a1b24
 *
 * time complexity
 * loop approach: O(n)
 * stream approach: O(n)
 * Java API approach: O(n)
 */
public class FindLargest {
  public static Integer findLargestLoop(List<Integer> numbers){
    Integer max = numbers.get(0);

    for (int i = 1; i < numbers.size(); i++) {
      if (max < numbers.get(i)) {
        max = numbers.get(i);
      }
    }
    return max;
  }

  public static  Integer findLargestStream(List<Integer> numbers){
    return  numbers.stream().mapToInt(x -> x).max().orElseThrow(NoSuchElementException::new);
  }

  public static Integer findLargestJavaAPI(List<Integer> numbers){
    return Collections.max(numbers);
  }
}
