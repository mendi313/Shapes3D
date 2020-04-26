package unittests.geometries;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing cylinder
 */
public class CylinderTests extends Object {
    /**
     * Test method for
     * {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //Test simple cylinder
        Ray r = new Ray(new Point3D(0, 1, 0), new Vector(0, 1, 0));
        Cylinder c1 = new Cylinder(4, r, 5);

        assertEquals("not good normal",
                new Vector(1, 0, 0),
                c1.getNormal(new Point3D(4, 3, 0)));

        //another Test
        Ray r1 = new Ray(Point3D.ZERO, new Vector(1, 1, 0));
        Cylinder c2 = new Cylinder(2, r1, 4);
        assertEquals("not good normal",
                new Vector(0, 0, 1),
                c2.getNormal(new Point3D(2, 2, 2)));

        //Test point on the base A
        assertEquals("not good normal for a point on the base A.",
                c2.get_ray().getDirection(),
                c2.getNormal(new Point3D(-0.5, 0.5, 0)));

        //Test point on the base B
        double sqrt = Math.sqrt(2d);
        assertEquals("not good normal for a point on the base B.",
                c2.get_ray().getDirection(),
                c2.getNormal(new Point3D(2 * sqrt, 2 * sqrt, 0)));

        // =============== Boundary Values Tests ==================

        //Test on the edge of the base A
        assertEquals("not good normal for a point on the edge of base A.",
                c2.get_ray().getDirection(),
                c2.getNormal(new Point3D(-1, 1, 0)));

        //Test on the edge of the base B
        assertEquals("not good normal for a point on the edge of base B.",
                c2.get_ray().getDirection(),
                c2.getNormal(new Point3D(2 * sqrt, 2 * sqrt, 2)));

        //Test point null
        try {
            c2.getNormal(null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testFindIntersections() {

        // ============ Equivalence Partitions Tests ==============

        // **** Group: Ray's line goes through the side of the cylinder

        Ray ray = new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0));
        Cylinder cylinder = new Cylinder(1d, ray, 3d);
        Point3D p1 = new Point3D(0, 2, 0);
        Point3D p2 = new Point3D(2, 2, 0);

        // TC01: Ray's line is outside the cylinder (0 points)
        List<Point3D> result = cylinder.findIntersections(new Ray(new Point3D(0, 4, 0),
                new Vector(1, 0, 0)));

       assertEquals("Ray's line out of cylinder", null, result.size());

        // TC02: Ray starts before and crosses the cylinder from the side (2 points)
        result = cylinder.findIntersections(new Ray(new Point3D(-1, 2, 0),
                new Vector(1, 0, 0)));

        assertEquals("Wrong number of points", 2, result.size());

        if (result.get(0).getX().get() > result.get(1).getX().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses cylinder", List.of(p1, p2), result);

        // TC03: Ray starts inside the cylinder (1 point)
        result = cylinder.findIntersections(new Ray(new Point3D(0.5, 2, 0),
                new Vector(1, 0, 0)));

        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray's crosses the cylinder", List.of(p2), result);

        // TC04: Ray starts after the cylinder (0 points)
        assertEquals("Ray's start point out of cylinder", null,
                cylinder.findIntersections(new Ray(new Point3D(3, 2, 0),
                        new Vector(1, 0, 0))));

        // **** Group: Ray's line goes through the bases of the cylinder

        Point3D p3 = new Point3D(0.5, 0, 0);
        Point3D p4 = new Point3D(0.5, 3, 0);

        // TC05: Ray starts before and crosses the cylinder (2 points)
       result = cylinder.findIntersections(new Ray(new Point3D(0.5, -1, 0),
                new Vector(0, 1, 0)));

        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getY().get() > result.get(1).getY().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray's crosses the cylinder", List.of(p3, p4), result);

        // TC06: Ray starts inside the cylinder (1 point)
        result = cylinder.findIntersections(new Ray(new Point3D(0.5, 1, 0),
                new Vector(0, 1, 0)));

        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray's crosses the cylinder", List.of(p4), result);

        // TC07: Ray starts after the cylinder (0 points)
        assertEquals("Ray's start point out of cylinder", null,
                cylinder.findIntersections(new Ray(new Point3D(0.5, 5, 0),
                        new Vector(0, 1, 0))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line goes through the side of the cylinder
        //TC11: Ray starts at cylinder and go inside (1 point)
        result = cylinder.findIntersections(new Ray(p1, new Vector(1, 0, 0)));

        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray's crosses the cylinder", List.of(p2), result);

        // TC12: Ray starts at cylinder and goes outside (0 points)
        assertEquals("Ray's start point the cylinder and go outside", null,
                cylinder.findIntersections(new Ray(p2, new Vector(1, 0, 0))));

        // **** Group: Ray's line goes through the bases of the cylinder
        //TC13: Ray starts at cylinder and go inside (1 point)
        result = cylinder.findIntersections(new Ray(p3, new Vector(0, 1, 0)));

        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray's crosses the cylinder", List.of(p4), result);

        // TC14: Ray starts at cylinder and goes outside (0 points)
        assertEquals("Ray's start point the cylinder and go outside", null,
                cylinder.findIntersections(new Ray(p4, new Vector(0, 1, 0))));
    }
}