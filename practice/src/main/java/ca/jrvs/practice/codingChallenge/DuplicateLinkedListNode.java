package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import java.util.HashSet;

public class DuplicateLinkedListNode {

  public static <T> LinkedJList removeDuplicates(LinkedJList<T> list) {
    HashSet<T> elementSet = new HashSet<>();
    LinkedJList<T> returnList = new LinkedJList<>();

    for (int i = 0; i < list.size(); i++) {
      if (!elementSet.contains(list.get(i))) {
        returnList.add(list.get(i));
        elementSet.add(list.get(i));
      }
    }
    return returnList;
  }
}
