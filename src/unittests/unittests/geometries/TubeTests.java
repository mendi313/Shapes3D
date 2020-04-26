package unittests.geometries;

import geometries.Sphere;
import geometries.Tube;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing Tube
 */
public class TubeTests extends Object {
    /**
     * Test method for
     * {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */

    // ============ Equivalence Partitions Tests ==============
    @Test
    public void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        //Test simple Tube
        Ray r = new Ray(new Point3D(0, 1, 0), new Vector(0, 1, 0));
        Tube t1 = new Tube(4, r);

        assertEquals("not good normal", new Vector(1, 0, 0), t1.getNormal(new Point3D(4, 0, 0)));

        //another Test
        Ray r1 = new Ray(Point3D.ZERO, new Vector(1, 1, 0));
        Tube t2 = new Tube(2, r1);
        assertEquals("", new Vector(0, 0, 1), t2.getNormal(new Point3D(2, 2, 2)));

        // =============== Boundary Values Tests ==================

        //Test point null
        try {
            t2.getNormal(null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testFindIntersections() {
        Ray ray = new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0));
        Point3D p1 = new Point3D(0, 2, 0);
        Point3D p2 = new Point3D(2, 2, 0);
        Tube tube = new Tube(1d, ray);

        // TC01: Ray's line is outside the tube (0 points)
        List<Point3D> result = tube.findIntersections(new Ray(new Point3D(0, 4, 3),
                new Vector(1, 0, 0)));

        assertEquals("Ray's line out of tube", null, result);

        // TC02: Ray starts before and crosses the tube (2 points)
        result = tube.findIntersections(new Ray(new Point3D(-1, 2, 0),
                new Vector(1, 0, 0)));

        assertEquals("Wrong number of points", 2, result.size());

        if (result.get(0).getX().get() > result.get(1).getX().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses tube", List.of(p1, p2), result);

        // TC03: Ray starts inside the tube (1 point)
        result = tube.findIntersections(new Ray(new Point3D(0.5, 2, 0),
                new Vector(1, 0, 0)));

        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray's crosses the tube", List.of(p2), result);

        // TC04: Ray starts after the tube (0 points)
        assertEquals("Ray's start point out of tube", null,
                tube.findIntersections(new Ray(new Point3D(3, 2, 0),
                        new Vector(1, 0, 0))));

        // =============== Boundary Values Tests ==================

        //TC11: Ray starts at tube and go inside (1 point)
        result = tube.findIntersections(new Ray(p1, new Vector(1, 0, 0)));

        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray's crosses the tube", List.of(p2), result);

        // TC12: Ray starts at tube and goes outside (0 points)
        assertEquals("Ray's start point the tube and go outside", null,
                tube.findIntersections(new Ray(p2, new Vector(1, 0, 0))));

    }
}