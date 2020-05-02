package unittests.geometries;

import geometries.*;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GeometriesTest {

    @Test
    public void findIntersections() {
        // =============== Boundary Values Tests ==================
        // TC01: empty collection (0 elements)
        Geometries geometries = new Geometries();
        Ray ray = new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0));

        assertNull("empty collection", geometries.findIntersections(ray));

        // TC02: no geometry is intersect (0 elements)
        ArrayList<Intersectable> intersectables = new ArrayList<>();
        intersectables.add(new Sphere(1d, new Point3D(3, 2, 0)));
        intersectables.add(new Cylinder(1d,
                new Ray(new Point3D(-2, 2, -1), new Vector(0, 0, 1)), 4d));
        geometries = new Geometries(intersectables);
        assertNull("no intersection points", geometries.findIntersections(ray));

        // TC03: one geometry is intersect (2 points)
        geometries.add(new Sphere(1d, new Point3D(1.5, 2, 0)));

        assertEquals("Wrong number of elements", 2, (geometries.findIntersections(ray)).size());

        // TC04: all geometries is intersected (3 points)
        intersectables = new ArrayList<Intersectable>(List.of(new Sphere(1d, new Point3D(1.5, 2, 0)),
                new Cylinder(1d,
                        new Ray(new Point3D(-2, 2, -1),
                                new Vector(0, 0, 1)), 4d),
                new Triangle(new Point3D(0, 6, -1),
                        new Point3D(2, 6, -1),
                        new Point3D(1, 6, 2))));
        geometries = new Geometries(intersectables);

        assertEquals("Wrong number of points", 3, (geometries.findIntersections(ray)).size());

        // ============ Equivalence Partitions Tests ==============

        // TC11: Some but not all geometries is intersected (5 points)
        geometries.add(new Sphere(1d, new Point3D(3, 2, 0)));

        assertEquals("Wrong number of points", 3, (geometries.findIntersections(ray)).size());
    }
}