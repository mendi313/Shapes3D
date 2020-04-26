package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

import java.util.List;

/**
 * Testing Polygons
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon #poligon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {
        }

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {
        }

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {
        }

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {
        }

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(
                new Point3D(0, 0, 1),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3).scale(-1), pl.getNormal(new Point3D(0, 0, 1)));
    }


    @Test
    public void testFindIntersections() {
        Polygon p2 = new Polygon(new Point3D(1, 2, 2),
                new Point3D(1, 1, 1),
                new Point3D(1, 3, 1));
        // ============ Equivalence Partitions Tests ==============

        // TC01: ray intersects inside polygon/triangle (1 points)
        List<Point3D> result = p2.findIntersections(new Ray(new Point3D(1, 2, 1.5),
                new Vector(1, 0, 1)));
//        assertEquals("ray intersects inside polygon", 1, result.size());
        // TC02: ray intersects outside against edge (0 points)
        assertEquals("ray intersects outside against edge", null,
                p2.findIntersections(new Ray(new Point3D(1, 1, 2),
                        new Vector(1, 0, 1))));
        // TC03: ray intersects outside against vertex (0 points)
        assertEquals("ray intersects outside against vertex", null,
                p2.findIntersections(new Ray(new Point3D(1, 2, 3),
                        new Vector(1, 0, 1))));

        // =============== Boundary Values Tests ==================

        // TC11: ray intersects on edge (0 points)
        assertEquals("ray intersects on edge", null,
                p2.findIntersections(new Ray(new Point3D(1, 3, 3),
                        new Vector(1, 0, 1))));
        // TC12: ray intersects in vertex (0 points)
        assertEquals("ray intersects in vertex", null,
                p2.findIntersections(new Ray(new Point3D(1, 2, 2),
                        new Vector(1, 0, 1))));
        // TC13: ray intersects on edge's continuation (0 points)
        assertEquals("ray intersects on edge's continuation", null,
                p2.findIntersections(new Ray(new Point3D(1, 4, 0),
                        new Vector(1, 0, 1))));
    }

}

