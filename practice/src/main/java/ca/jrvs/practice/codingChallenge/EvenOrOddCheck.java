package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-2ab008d921d8471ea5a0cc6c63e4b020
 */
class EvenOrOddCheck {

  /**
   * Big-O: O(1)
   * Justification: arithmetic operation
   * Considerations: using primitive int prevents num from being null
   * @param num
   * @return string even or odd
   */
  public static String moduloApproach(int num){
    return num % 2 == 0 ? "even" : "odd";
  }

  /**
   *Big-O: O(1)
   * Justification: bit operations
   * Considerations: using primitive int prevents num from being null
   * @param num
   * @return string even or odd
   */
  public static String bitOperatorApproach(int num){
    return (num ^ 1) == num + 1 ? "even" : "odd";
  }
}