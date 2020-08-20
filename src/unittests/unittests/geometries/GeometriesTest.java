package unittests.geometries;

import geometries.*;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing Geometries class is here
 */
public class GeometriesTest {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersectionsRay() {
        List<Intersectable.GeoPoint> result;
        Geometries geos = new Geometries(
                new Plane(new Point3D(2, 0, 0), new Vector(-1, 1, 0)),
                new Sphere(2d, new Point3D(5,0,0)),
                new Triangle(new Point3D(8.5, -1, 0), new Point3D(7.5, 1.5, 1), new Point3D(7.5, 1.5, -1))
        );

        // ============ Equivalence Partitions Tests ==============
        // TC01: Some geo intersect
        result = geos.findGeoIntersections(new Ray(new Point3D(1, 0, 0), new Vector(7, 3, 0)));
        assertNotNull("It is empty!", result);
        assertEquals("Bad intersects", 3, result.size());

        // =============== Boundary Values Tests ==================
        // TC11: Empty collection
        result = new Geometries().findGeoIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0)));
        assertNull("It is not empty!", result);

        // TC12: None geo intersect
        result = geos.findGeoIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 3, 0)));
        assertNull("Bad intersects", result);

        // TC13: Single geo intersect
        result = geos.findGeoIntersections(new Ray(new Point3D(1, 0, 0), new Vector(4, 3, 0)));
        assertNotNull("It is empty!", result);
        assertEquals("Bad intersects", 1, result.size());

        // TC14: All geo intersect
        result = geos.findGeoIntersections(new Ray(new Point3D(1, 0, 0), new Vector(7, 1, 0)));
        assertNotNull("It is empty!", result);
        assertEquals("Bad intersects", 4, result.size());

    }

}

