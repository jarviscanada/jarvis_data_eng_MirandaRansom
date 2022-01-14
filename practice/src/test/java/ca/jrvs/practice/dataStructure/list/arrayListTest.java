package ca.jrvs.practice.dataStructure.list;

import org.junit.Assert;
import org.junit.Test;

public class arrayListTest {
  ArrayJList list = new ArrayJList();

  @Test
  public void TestAdd(){
    Assert.assertTrue(list.add("element"));
  }

  @Test
  public void TestGrow(){
    list.add(1);  //add elements to force grow
    list.add(2);  //at default capacity 10
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(6);
    list.add(7);
    list.add(8);
    list.add(9);
    list.add(10);
    Assert.assertTrue(list.add(11));  //ensure proper add after grow
  }

  @Test
  public void TestToArray(){
    Object array[] = new Object[] {"element"};
    list.add("element");
    Assert.assertArrayEquals(array, list.toArray());
  }

  @Test
  public void TestSize(){
    list.add("element");
    Assert.assertEquals(1, list.size());
  }

  @Test
  public void TestIsEmpty(){
    Assert.assertTrue(list.isEmpty());
  }

  @Test
  public void TestIndexOf(){
    list.add(1);
    Assert.assertEquals(0, list.indexOf(1));
  }

  @Test
  public void TestContains(){
    list.add(100);
    Assert.assertTrue(list.contains(100));
  }

  @Test
  public void TestGet(){
    list.add("element");
    Assert.assertEquals("element", list.get(0));
  }

  @Test
  public void TestRemove(){
    list.add("element");
    list.remove(0);
    Assert.assertNull(list.get(0));
  }

  @Test
  public void TestClear(){
    list.add("element");
    list.clear();
    Assert.assertTrue(list.isEmpty());
  }

}
