package homework2;

/**
 * Created by Monsters on 24.11.2016.
 */
public  class Point {
  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }


  public  double distanceBetweenPoints(Point p1,Point p2){
  return Math.sqrt(( p2.x-p1.x)*( p2.x-p1.x)+(p2.y -p1.y)*(p2.y -p1.y));
  }
}
