package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import org.junit.Test;

public class RemoveNthFromEndLinkedListTest {
  @Test
  public void testRemoveFromEnd() {
    LinkedJList<Integer> list = new LinkedJList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);

    LinkedJList returnList = RemoveNthFromEndLinkedList.removeFromEnd(list, 2);
    for (int i = 0; i < returnList.size(); i++) {
      System.out.println(list.get(i));
    }
  }

}
