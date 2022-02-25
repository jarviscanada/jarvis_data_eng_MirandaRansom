package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-34bfa7f2b07240cc88f1643cfae0e427
 *
 * time complexity:
 * push O(n)
 * pop O(1)
 * peek O(1)
 */

import java.util.Stack;

public class ImpQueueWStack {
  static Stack<Integer> stack = new Stack<>();
  static Stack<Integer> shift = new Stack<>();

  private int size = 0;

  public void push(int num) {
    while (!stack.isEmpty()) {
      shift.push(stack.pop());
    }

    stack.push(num);
    size++;
    while (!shift.isEmpty()) {
      stack.push(shift.pop());
    }
  }

  public Integer pop() {
    size--;
    return stack.pop();
  }

  public int peek() {
    return stack.peek();
  }

  public int getSize() {
    return size;
  }



}
