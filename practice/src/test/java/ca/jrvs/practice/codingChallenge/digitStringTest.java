package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class digitStringTest {
  @Test
  public void testASCIIMethod(){
    //fail path
    String failString = "fail test";
    Assert.assertFalse(digitString.isDigitStringASCII(failString));

    //happy path
    String passString = "900";
    Assert.assertTrue(digitString.isDigitStringASCII(passString));

  }

  @Test
  public void testJavaAPIMethod() {
    //fail path
    String failString = "9,000";
    Assert.assertFalse(digitString.isDigitStringJavaAPI(failString));

    //happy path
    String passString = "9000";
    Assert.assertTrue(digitString.isDigitStringJavaAPI(passString));
  }

  @Test
  public void testRegexMethod(){
    //fail path
    String failString = "85.401";
    Assert.assertFalse(digitString.isDigitStringRegex(failString));

    //happy path
    String passString = "1001";
    Assert.assertTrue(digitString.isDigitStringRegex(passString));
  }
}
