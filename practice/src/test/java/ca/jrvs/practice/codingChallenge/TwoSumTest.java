package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class TwoSumTest {
  int[] arr = new int[] {1, 3, 7, 4};
  int[] expected = new int[] {0, 2};

  @Test
  public void testTwoSumNaive(){
    int[] ret = TwoSum.twoSumNaive(arr, 8);
    Assert.assertArrayEquals(expected, ret);
  }

  @Test
  public void testTwoSumLinear(){
    int[] ret = TwoSum.twoSumLinear(arr, 8);
    Assert.assertArrayEquals(expected, ret);
  }

}
