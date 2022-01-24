package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-2b5637e90e55469687831297176d6e73
 */
public class ImpStackWQueue {
  //implement stack using 2 queues
  static Queue<Integer> pushQ = new LinkedList<>();
  static Queue<Integer> popQ = new LinkedList<>();

  int size = 0;

  void push(int num){
    size++;

    pushQ.add(num);

    while (!popQ.isEmpty()) {
      pushQ.add(popQ.peek());
      popQ.remove();
    }

    Queue<Integer> temp = popQ;
    popQ = pushQ;
    pushQ = temp;
  }

  int pop(){
    if (popQ.isEmpty()) {
      return -1;
    }
    size--;
    return popQ.remove();
  }

  int peek(){
    if (popQ.isEmpty()) {
      return -1;
    }
    return popQ.peek();
  }

  int getSize(){
    return size;
  }

  //implement stack using a single queue
  public static class StackW1Queue{
    Queue<Integer> queue = new LinkedList<>();

    public void push(int num) {

      queue.add(num);

      for (int i = 0; i < queue.size()-1; i++) {
        int x = queue.remove();
        queue.add(x);
      }
    }

    public int pop(){
      if (queue.isEmpty()) {
        return -1;
      }
      return queue.remove();
    }

    public int peek(){
      if (queue.isEmpty()) {
        return -1;
      }
      return queue.peek();
    }

    public int getSize(){
      return queue.size();
    }
  }
}
