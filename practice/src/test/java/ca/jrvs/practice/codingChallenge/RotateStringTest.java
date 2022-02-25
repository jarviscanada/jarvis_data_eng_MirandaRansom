package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class RotateStringTest {
  @Test
  public void test() {
    String string = "abcde";
    //fail case
    String failGoal = "abced";
    Assert.assertFalse(RotateString.rotateString(string, failGoal));

    //pass case
    String passGoal = "cdeab";
    Assert.assertTrue(RotateString.rotateString(string, passGoal));

    //length check test
    String lengthTest = "a";
    Assert.assertFalse(RotateString.rotateString(string, lengthTest));
  }
}
