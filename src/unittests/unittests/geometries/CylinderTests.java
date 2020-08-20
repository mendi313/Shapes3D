package unittests.geometries;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static org.junit.Assert.*;

/**
 * Tube class tests
 */
public class CylinderTests {

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        Cylinder cyl = new Cylinder(1.0, new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 0)), 1d);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Point at a side of the cylinder
        assertEquals("Bad normal to cylinder", new Vector(0, 0, 1), cyl.getNormal(new Point3D(0, 0.5, 2)));

        // TC02: Point at a 1st base of the cylinder
        assertEquals("Bad normal to lower base of cylinder", new Vector(0, 1, 0), cyl.getNormal(new Point3D(0, 0, 1.5)));

        // TC03: Point at a 2nd base of the cylinder
        assertEquals("Bad normal to upper base of cylinder", new Vector(0, 1, 0), cyl.getNormal(new Point3D(0, 1, 0.5)));

        // =============== Boundary Values Tests ==================
        // TC11: Point at the center of 1st base
        assertEquals("Bad normal to center of lower base", new Vector(0, 1, 0), cyl.getNormal(new Point3D(0, 0, 1)));

        // TC12: Point at the center of 2nd base
        assertEquals("Bad normal to center of upper base", new Vector(0, 1, 0), cyl.getNormal(new Point3D(0, 1, 1)));

        // TC13: Point at the edge with 1st base
        assertEquals("Bad normal to edge with lower base", new Vector(0, 1, 0), cyl.getNormal(new Point3D(0, 0, 2)));

        // TC14: Point at the edge with 2nd base
        assertEquals("Bad normal to edge with upper base", new Vector(0, 1, 0), cyl.getNormal(new Point3D(0, 1, 2)));

    }

}
