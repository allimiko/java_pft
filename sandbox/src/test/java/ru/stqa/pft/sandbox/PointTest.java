package ru.stqa.pft.sandbox;

import homework2.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Monsters on 27.11.2016.
 */
public class PointTest {


  @Test
  public void testArea(){
    Point s = new Point(3.0 ,3.0);
    Point s1 = new Point(5.0 ,5.0);
    Assert.assertEquals(s.distanceBetweenPoints( s,  s1),2.8284271247461903);
  }
}
