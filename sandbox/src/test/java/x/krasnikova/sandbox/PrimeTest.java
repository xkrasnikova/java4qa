package x.krasnikova.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTest {
  @Test
  public void testPrimes(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testNotPrimes(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE -2));
  }
}
