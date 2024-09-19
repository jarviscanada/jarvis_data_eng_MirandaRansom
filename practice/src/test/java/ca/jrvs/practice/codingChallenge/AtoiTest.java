package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class AtoiTest {
  @Test
  public void testAtoiJavaLibrary(){
    Assert.assertEquals(42, Atoi.atoiJavaLibrary("42"));
  }

  @Test
  public void testAtoiManual(){
    Assert.assertEquals(42, Atoi.atoiManual("42"));
  }
}