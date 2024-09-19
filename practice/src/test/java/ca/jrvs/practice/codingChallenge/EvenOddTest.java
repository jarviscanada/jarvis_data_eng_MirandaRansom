package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class EvenOddTest {
  @Test
  public void TestModuloEven(){
    Assert.assertEquals("even", EvenOrOddCheck.moduloApproach(20));
  }
  @Test
  public void TestModuloOdd(){
    Assert.assertEquals("odd", EvenOrOddCheck.moduloApproach(15));
  }
  @Test
  public void TestBitEven(){
    Assert.assertEquals("even", EvenOrOddCheck.bitOperatorApproach(20));
  }
  @Test
  public void TestBitOdd(){
    Assert.assertEquals("odd", EvenOrOddCheck.bitOperatorApproach(15));
  }
}
