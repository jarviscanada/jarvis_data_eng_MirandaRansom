package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class ValidParenthesesTest {
  ValidParentheses validParentheses = new ValidParentheses();

  @Test
  public void testIsValid() {
    //is valid path
    String validPattern = "({}[])" ;
    Assert.assertTrue(validParentheses.isValid(validPattern));

    //is not valid path
    String invalidPattern = "){";
    Assert.assertFalse(validParentheses.isValid(invalidPattern));
  }

}
