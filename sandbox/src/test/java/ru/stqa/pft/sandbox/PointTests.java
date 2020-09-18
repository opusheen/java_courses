package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PointTests {

    @Test
    public void testDistanceAssert () {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    }



    @Test
    public void testDistanceNotNegative () {
        Point p1 = new Point (4,3);
        Point p2 = new Point (2,3);
        Assert.assertNotEquals(Math.signum(p1.distance(p2)), -1.0 );




    }
}
