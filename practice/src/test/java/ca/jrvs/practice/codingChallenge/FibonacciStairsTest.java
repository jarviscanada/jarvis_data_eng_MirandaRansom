package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciStairsTest {
  @Test
  public void testRecursiveFib(){
    Assert.assertEquals(5, fibonacciStairs.recursiveFib(5));
  }

  @Test
  public void testDynamicFib(){
    Assert.assertEquals(5, fibonacciStairs.dynamicFib(5));
  }
}
