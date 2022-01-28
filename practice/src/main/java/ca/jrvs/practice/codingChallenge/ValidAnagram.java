package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Anagram-b8708bd705574ecfa14ebf5cbb759c95
 *
 * time complexity
 * sorting method: O (n log n)
 * hashing method: O(n)
 */
public class ValidAnagram {

  public static boolean isAnagramSorrt(String string1, String string2) {
    char[] array1 = string1.toCharArray();
    char[] array2 = string2.toCharArray();

    Arrays.sort(array1);
    Arrays.sort(array2);

    string1 = Arrays.toString(array1);
    string2 = Arrays.toString(array2);

    if (string1.equalsIgnoreCase(string2)) {
      return true;
    }

    return false;
  }

  public static boolean isAnagramHash(String string1, String string2) {
    char[] array1 = string1.toCharArray();
    char[] array2 = string2.toCharArray();
    HashMap<Character, Integer> hash1 = new HashMap<>();
    HashMap<Character, Integer> hash2 = new HashMap<>();

    for (int i = 0; i < array1.length; i++) {
      hash1.put(array1[i], hash1.containsKey(array1[i]) ? hash1.get(array1[i]) + 1  : 1);
      hash2.put(array2[i], hash2.containsKey(array2[i]) ? hash2.get(array2[i]) + 1  : 1);
    }
    return hash1.equals(hash2);
  }
}
