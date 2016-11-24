package homework2;

/**
 * Created by Monsters on 24.11.2016.
 */
public class Main {
 static Point p1 = new Point(4.45,5.62);
 static Point p2 = new Point(8.45,8.62);

 public static double distance(Point p1,Point p2) {
   return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
 }

  public static void main(String[] args) {
    System.out.println("Paccтояние между точками = "+ distance(p1,p2) );
    System.out.println("Paccтояние между точками = "+ p2.distanceBetweenPoints(p1,p2) );
  }
}
