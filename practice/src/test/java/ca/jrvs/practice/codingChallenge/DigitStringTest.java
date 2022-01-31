package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class DigitStringTest {
  @Test
  public void testASCIIMethod(){
    //fail path
    String failString = "fail test";
    Assert.assertFalse(DigitString.isDigitStringASCII(failString));

    //happy path
    String passString = "900";
    Assert.assertTrue(DigitString.isDigitStringASCII(passString));

  }

  @Test
  public void testJavaAPIMethod() {
    //fail path
    String failString = "9,000";
    Assert.assertFalse(DigitString.isDigitStringJavaAPI(failString));

    //happy path
    String passString = "9000";
    Assert.assertTrue(DigitString.isDigitStringJavaAPI(passString));
  }

  @Test
  public void testRegexMethod(){
    //fail path
    String failString = "85.401";
    Assert.assertFalse(DigitString.isDigitStringRegex(failString));

    //happy path
    String passString = "1001";
    Assert.assertTrue(DigitString.isDigitStringRegex(passString));
  }
}
