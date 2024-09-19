package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class FindLargestTest {
  @Test
  public void testLoop(){
    ArrayList numbers = new ArrayList<>(Arrays.asList(1, 4, 6, 9, 10, 3, 2));
    Assert.assertEquals((Integer) 10, FindLargest.findLargestLoop(numbers));
  }

  @Test
  public void testStream(){
    ArrayList numbers = new ArrayList<>(Arrays.asList(1, 4, 6, 9, 10, 3, 2));
    Assert.assertEquals((Integer) 10, FindLargest.findLargestStream(numbers));
  }

  @Test
  public void testJavaAPI(){
    ArrayList numbers = new ArrayList<>(Arrays.asList(1, 4, 6, 9, 10, 3, 2));
    Assert.assertEquals((Integer) 10, FindLargest.findLargestJavaAPI(numbers));
  }
}
