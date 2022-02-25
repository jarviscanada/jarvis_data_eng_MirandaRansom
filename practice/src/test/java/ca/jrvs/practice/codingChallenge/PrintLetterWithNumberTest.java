package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.PrintLetterWithNumber;
import org.junit.Assert;
import org.junit.Test;

public class PrintLetterWithNumberTest {
  @Test
  public void testPrintLetterWithNumber() {
    String string = "abcee";
    String expected = "a1b2c3e5e5";
    Assert.assertEquals(expected, PrintLetterWithNumber.printLetterWithNumber(string));
  }
}
