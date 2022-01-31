package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-13bc5cf4078247aea436555247186208
 */
public class digitString {
  public static boolean isDigitStringASCII(String string) {
    char[] stringArray = string.toCharArray();

    for (char ch : stringArray) {
      if ( '0' > ch || ch > '9') {
        return false;
      }
    }
    return true;
  }

  public static boolean isDigitStringJavaAPI(String string) {
    try {
      Integer.valueOf(string);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public static boolean isDigitStringRegex(String string) {
    boolean matches = string.matches("[0-9]+");
    return matches;
  }
}
