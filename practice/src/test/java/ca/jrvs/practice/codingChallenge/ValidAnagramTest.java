package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class ValidAnagramTest {
  @Test
  public void TestSortMethod() {
    String string1 = "anagram";
    String string2 = "nagaram";

    Assert.assertTrue(ValidAnagram.isAnagramSorrt(string1, string2));
  }

  @Test
  public void TestHashMethod() {
    //fail case
    String failString1 = "house";
    String failString2 = "terra";
    Assert.assertFalse(ValidAnagram.isAnagramHash(failString1, failString2));

    //pass case
    String passString1 = "anagram";
    String passString2 = "nagaram";

    Assert.assertTrue(ValidAnagram.isAnagramHash(passString1, passString2));
  }

}
