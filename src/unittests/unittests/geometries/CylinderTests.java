package unittests.geometries;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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
}