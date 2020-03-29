package unittests.geometries;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * Testing plane
 */
public class PlaneTests extends Object {

    /**
     * Test method for
     * {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */

    // ============ Equivalence Partitions Tests ==============
    @Test
    public void testGetNormal() {
        Plane pl1 = new Plane(new Point3D(0, 1, 0), new Point3D(1, 0, 0), new Point3D(0, 0, 1));
        Vector v1 = pl1.getNormal();

        Plane pl2 = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        Vector v2 = pl1.getNormal();

        Plane pl3 = new Plane(new Point3D(1, 0, 0), new Point3D(0, 0, 1), new Point3D(0, 1, 0));
        Vector v3 = pl1.getNormal();

        assertEquals(v1, v3);
        assertEquals(v1, v2);
    }
}