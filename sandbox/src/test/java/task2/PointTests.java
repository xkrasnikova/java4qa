package task2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testArea(){
    Point p1 = new Point(0,0);
    Point p2 = new Point(0, 5);
    Assert.assertEquals(p2.distance(p1), 5.0);
  }

  @Test
  public void testArea2(){
    Point p1 = new Point(0,3);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p2.distance(p1), 3.0);
  }

  @Test
  public void testArea3(){
    Point p1 = new Point(0,0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p2.distance(p1), 0.0);
  }
}
