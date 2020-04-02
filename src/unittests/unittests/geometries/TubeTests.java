package unittests.geometries;

import geometries.Sphere;
import geometries.Tube;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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

     /*   //Test point not on the Tube
        try {
            Vector v = t2.getNormal(new Point3D(0, 0, 3));
            fail("not thrown Exception.");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }*/

        //Test point null
        try {
            t2.getNormal(null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }
}