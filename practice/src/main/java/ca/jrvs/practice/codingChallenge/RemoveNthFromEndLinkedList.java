package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.list.LinkedJList;

public class RemoveNthFromEndLinkedList {
  public static <T>LinkedJList removeFromEnd(LinkedJList list, int indexFromEnd) {
    int index = list.size() - indexFromEnd;
    list.remove(index);
    return list;
  }
}
