package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class evenOddTest {
  @Test
  public void TestModuloEven(){
    Assert.assertEquals("even", evenOrOddCheck.moduloApproach(20));
  }
  @Test
  public void TestModuloOdd(){
    Assert.assertEquals("odd", evenOrOddCheck.moduloApproach(15));
  }
  @Test
  public void TestBitEven(){
    Assert.assertEquals("even", evenOrOddCheck.bitOperatorApproach(20));
  }
  @Test
  public void TestBitOdd(){
    Assert.assertEquals("odd", evenOrOddCheck.bitOperatorApproach(15));
  }
}
