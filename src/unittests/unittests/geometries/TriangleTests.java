package unittests.geometries;

import org.junit.Test;
import geometries.*;
import primitives.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing triangle
 */
public class TriangleTests extends Object {
    /**
     * Test method for
     * {@link geometries.Triangle #triangle(primitives.Point3D)}.
     */

    // ============ Equivalence Partitions Tests ==============
    @Test
    public void getNormal() {
        Triangle triangle = new Triangle(new Point3D(-1, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 0, 1));
        Vector vector = new Vector(0, -1, 0).normalize();
        assertEquals(vector, triangle.getNormal(new Point3D(-1, 0, 0)));
        assertEquals(vector, triangle.getNormal(new Point3D(0, 0, 0)));
        assertEquals(vector, triangle.getNormal(new Point3D(0.5, 0, 0)));
    }

    @Test
    public void findIntersectionsTest() {
        Triangle triangle = new Triangle(new Point3D(0, 0, 0),
                new Point3D(1, 2, 0),
                new Point3D(2, 0, 0));
        //===========================================================//
        //===================EP: Three cases:=======================//
        // TC01: Inside triangle (1 point)
        primitives.Ray TC01 = new primitives.Ray(new primitives.Point3D(1.0, 1.0, -1.0), new primitives.Vector(0.0, 0.0, 1.0));
        List<Intersectable.GeoPoint> resultTC01 = triangle.findIntersections(TC01);

        assertEquals("Wrong number of points", 1, resultTC01.size());

        assertEquals("Ray crosses Triangle", java.util.List.of(new primitives.Point3D(1, 1, 0)), resultTC01);
        // TC021:  Outside against edge (0 point)
        primitives.Ray TC021 = new primitives.Ray(new primitives.Point3D(2.0, 1.0, -1.0), new primitives.Vector(0.0, 0.0, 1.0));
        List<Intersectable.GeoPoint> resultTC021 = triangle.findIntersections(TC021);

        assertEquals("Wrong number of points", null, resultTC021);

        // TC022: Outside against vertex (0 points)
        primitives.Ray TC022 = new primitives.Ray(new primitives.Point3D(2.0, -0.5, -1.0), new primitives.Vector(0.0, 0.0, 1.0));
        java.util. List<Intersectable.GeoPoint> resultTC022 = triangle.findIntersections(TC022);

        assertEquals("Wrong number of points", null, resultTC022);
    }
}