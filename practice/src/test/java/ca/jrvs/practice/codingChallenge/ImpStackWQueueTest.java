package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.ImpStackWQueue.StackW1Queue;
import org.junit.Assert;
import org.junit.Test;

public class ImpStackWQueueTest {
  ImpStackWQueue stack2 = new ImpStackWQueue();
  StackW1Queue stack1 = new StackW1Queue();

  //test implementation with 2 queues
  @Test
  public void testPush2(){
    stack2.push(4);
    Assert.assertEquals(1, stack2.getSize());
    Assert.assertEquals(4, stack2.peek());
  }

  @Test
  public void testPop2(){
    stack2.push(1);
    Assert.assertEquals(1, stack2.pop());
    Assert.assertEquals(0, stack2.getSize());
  }

  @Test
  public void testPeek2(){
    stack2.push(1);
    stack2.push(2);
    stack2.push(3);
    Assert.assertEquals(3, stack2.peek());
  }

  //test implementation with 1 queue
  @Test
  public void testPush1(){
    stack1.push(1);
    Assert.assertEquals(1, stack1.getSize());
    Assert.assertEquals(1, stack1.pop());
  }

  @Test
  public void testPop1() {
    stack1.push(5);
    Assert.assertEquals(5, stack1.pop());
    Assert.assertEquals(0, stack1.getSize());
  }

  @Test
  public void testPeek1() {
    stack1.push(1);
    stack1.push(2);
    stack1.push(3);
    Assert.assertEquals(3, stack1.peek());
  }

}
