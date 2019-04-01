package krasnikova.rest.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class SkipTestsTest extends TestBase{
  @Test
  public void testSkippedRest() throws IOException {
    skipIfNotFixedRest(1);
  }
}
