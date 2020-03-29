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

        Tube t1 = new Tube(4, new Ray(new Point3D(0, 0, 0), new Vector(new Coordinate(0), new Coordinate(2), new Coordinate(1))));
        Tube t2 = new Tube(4, new Ray(new Point3D(0, 0, 0), new Vector(new Coordinate(0), new Coordinate(2), new Coordinate(1))));


        assertTrue(t1.getNormal(new Point3D(0, 0, 4)).equals(new Vector(new Point3D(0, 0, 1))));
        //assertTrue(t1.getNormal(new Point3D(0, 0, -4)).equals(new Vector(new Point3D(0, 0, -1))));
        // assertTrue(t1.getNormal(new Point3D(0, 4, 0)).equals(new Vector(new Point3D(0, 1, 0))));
        // assertTrue(t1.getNormal(new Point3D(0, -4, 0)).equals(new Vector(new Point3D(0, -1, 0))));
        // assertTrue(t1.getNormal(new Point3D(4, 0, 0)).equals(new Vector(new Point3D(1, 0, 0))));
        //assertTrue(t1.getNormal(new Point3D(-4, 0, 0)).equals(new Vector(new Point3D(-1, 0, 0))));
        //assertTrue(t2.getNormal(new Point3D(1, 1, 0)).equals(new Vector(new Point3D(0, 0, -1))));
        // assertTrue(t2.getNormal(new Point3D(0, 1, 1)).equals(new Vector(new Point3D(-1, 0, 0))));
        //assertTrue(t2.getNormal(new Point3D(1, 0, 1)).equals(new Vector(new Point3D(0, -1, 0))));
    }
}