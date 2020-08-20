package unittests.geometries;
import geometries.Intersectable;
import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static org.junit.Assert.*;

/**
 * @author Dan
 *
 */
public class PlaneTests {

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormalPoint3D() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to plane", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testfindIntersectionsRay() {
        Plane pl = new Plane(new Point3D(0, 0, 1), new Vector(1, 1, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
        assertEquals("Bad plane intersection", List.of(new Point3D(1, 0, 0)),
                pl.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0))));

        // TC02: Ray out of plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))));

        // =============== Boundary Values Tests ==================
        // TC11: Ray parallel to plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 1, -1))));

        // TC12: Ray in plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(0, 0.5, .5), new Vector(0, 1, -1))));


        // TC13: Orthogonal ray into plane
        assertEquals("Bad plane intersection", List.of(new Point3D(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(-1, -1, -1))));

        // TC14: Orthogonal ray out of plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))));

        // TC15: Orthogonal ray out of plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))));

        // TC16: Orthogonal ray from plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 1))));

        // TC17: Ray from plane
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 0))));

        // TC18: Ray from plane's Q point
        assertNull("Must not be plane intersection",
                pl.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 0))));
    }
}
