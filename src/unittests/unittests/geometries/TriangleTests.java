package unittests.geometries;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

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

}