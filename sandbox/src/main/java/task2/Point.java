package task2;

public class Point {
 int x;
 int y;

 public Point(int x, int y){
   this.x = x;
   this.y = y;
 }

  public double distance (Point p1){
    return Math.sqrt(Math.pow((this.x-p1.x), 2) +  Math.pow((this.y-p1.y), 2));
  }

}
