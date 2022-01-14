package ca.jrvs.practice.dataStructure.list;

import org.junit.Assert;
import org.junit.Test;

public class linkedListTest {
  LinkedJList<String> list = new LinkedJList<>();

  @Test
  public void testAdd(){
    Assert.assertTrue(list.add("element"));
  }

  @Test
  public void testToArray(){
    String array[] = new String[] {"element"};
    list.add("element");
    Assert.assertArrayEquals(array, list.toArray());
  }

  @Test
  public void testSize(){
    Assert.assertEquals(0, list.size);
  }

  @Test
  public void testIsEmpty(){
    Assert.assertTrue(list.isEmpty());
  }

  @Test
  public void testIndexOf(){
    list.add("element");
    Assert.assertEquals(0, list.indexOf("element"));
  }

  @Test
  public void testContains(){
    list.add("element");
    Assert.assertTrue(list.contains("element"));
  }

  @Test
  public void testGet(){
    list.add("element");
    Assert.assertEquals("element", list.get(0));
  }

  @Test
  public void testRemove(){
    list.add("element");
    Assert.assertEquals("element",  list.remove(0));
  }

  @Test
  public void testClear(){
    list.add("element");
    list.clear();
    Assert.assertTrue(list.isEmpty());
  }

}
