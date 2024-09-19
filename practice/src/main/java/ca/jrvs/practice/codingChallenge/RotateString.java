package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Rotate-String-952107e8f3594307851e34a6173c076a
 *
 * time complexity: O(n)
 */
public class RotateString {
  public static boolean rotateString(String string, String goal) {
    if (string.length() != goal.length()) {
      return false;
    }

    StringBuilder rotator = new StringBuilder(string);
    for (int i = 0; i < string.length(); i++) {
      rotator = rotator.append(rotator.charAt(0));
      rotator = rotator.deleteCharAt(0);
      string = rotator.toString();

      if (string.contains(goal)) {
        return true;
      }
    }
    return false;
  }
}
