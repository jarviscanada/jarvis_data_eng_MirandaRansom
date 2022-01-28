package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class ImpQueueWStackTest {
  ImpQueueWStack queue = new ImpQueueWStack();

  @Test
  public void testPush(){
    queue.push(1);
    Assert.assertEquals(1, queue.getSize());
    Assert.assertEquals(1, queue.peek());
  }

  @Test
  public void testPop(){
    queue.push(1);
    int value = queue.pop();
    Assert.assertEquals(1, value);
    Assert.assertEquals(0, queue.getSize());
  }

  @Test
  public void testPeek() {
    queue.push(1);
    queue.push(2);
    queue.push(3);

    Assert.assertEquals(1, queue.peek());
  }

}
