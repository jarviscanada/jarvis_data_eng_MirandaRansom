package ca.jrvs.practice.codingChallenge;


/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-b1b053a2c04046bda20979a3636aaec7
 *
 * time complexity:
 * pointers approach: O(n)
 * recursive approach: O(n)
 */
public class ValidPalindrome {
  public static boolean isValidPointers(String string) {
    if (string == null) {
      return false;
    }
    string = string.toLowerCase();
    char[] charArray = string.toCharArray();
    int leftPointer = 0;
    int rightPointer = charArray.length -1;

    while (leftPointer < rightPointer) {
      while (!Character.isLetterOrDigit(charArray[leftPointer]) && leftPointer < rightPointer) {
        leftPointer++;
      }
      while (!Character.isLetterOrDigit(charArray[rightPointer]) && leftPointer < rightPointer) {
        rightPointer--;
      }
      if (charArray[leftPointer] != charArray[rightPointer]) {
        return false;
      }
      leftPointer++;
      rightPointer--;
    }
    return true;
  }

  public static boolean isValidRecursive (String string) {
    string = string.toLowerCase();
    //base case
    if (string == null) {
      return false;
    }
    if (string.length() == 1) {
      return true;
    }
    if (string.equals("")) {
      return true;
    }

    //recursive step
    if (!Character.isLetterOrDigit(string.charAt(0))) { //trim string front
      StringBuilder newString = new StringBuilder(string);
      newString.deleteCharAt(0);
      return isValidRecursive(newString.toString());
    }
    if (!Character.isLetterOrDigit(string.charAt(string.length() - 1))) {
      StringBuilder newString = new StringBuilder(string);
      newString.deleteCharAt(string.length() - 1);
      return isValidRecursive(newString.toString());
    }
    if (string.charAt(0) == string.charAt(string.length() - 1)) {
      StringBuilder newString = new StringBuilder(string);
      newString.deleteCharAt(string.length() - 1);
      newString.deleteCharAt(0);
      return isValidRecursive(newString.toString());
    } else {
      return false;
    }
  }
}
