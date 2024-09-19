package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import org.junit.Assert;
import org.junit.Test;

public class DuplicateLinkedListNodeTest {
  @Test
  public void testRemoveDuplicates() {
    LinkedJList<Integer> list = new LinkedJList<>();
    list.add(5);
    list.add(4);
    list.add(5);
    list.add(3);

    LinkedJList<Integer> returnList = DuplicateLinkedListNode.removeDuplicates(list);

    for (int i = 0; i < returnList.size(); i++) {
      System.out.println(returnList.get(i));
    }
  }
}
