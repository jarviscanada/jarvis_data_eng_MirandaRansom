package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class ValidPalindromeTest {

  @Test
  public void testIsValidPointers() {
    //fail case
    String string = "abcdefg";
    Assert.assertFalse(ValidPalindrome.isValidPointers(string));

    //pass case
    string = "aa";
    Assert.assertTrue(ValidPalindrome.isValidPointers(string));

    string = "A man, a plan, a canal: Panama";
    Assert.assertTrue(ValidPalindrome.isValidPointers(string));
  }

  @Test
  public void testIsValidRecursive() {
    //fail case
    String string = "abcdefg";
    Assert.assertFalse(ValidPalindrome.isValidRecursive(string));

    //pass case
    string = "aa";
    Assert.assertTrue(ValidPalindrome.isValidRecursive(string));

    string = "A man, a plan, a canal: Panama";
    Assert.assertTrue(ValidPalindrome.isValidRecursive(string));
  }

}
