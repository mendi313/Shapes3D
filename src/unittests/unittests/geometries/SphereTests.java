package unittests.geometries;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class SphereTests extends Object {

    @Test
    public void getNormalTest() {
        Sphere s1 = new Sphere(4, new Point3D(0, 0, 0));
        Sphere s2 = new Sphere(1, new Point3D(1, 1, 1));


        assertTrue(s1.getNormal(new Point3D(0, 0, 4)).equals(new Vector(new Point3D(0, 0, 1))));
        assertTrue(s1.getNormal(new Point3D(0, 0, -4)).equals(new Vector(new Point3D(0, 0, -1))));
        assertTrue(s1.getNormal(new Point3D(0, 4, 0)).equals(new Vector(new Point3D(0, 1, 0))));
        assertTrue(s1.getNormal(new Point3D(0, -4, 0)).equals(new Vector(new Point3D(0, -1, 0))));
        assertTrue(s1.getNormal(new Point3D(4, 0, 0)).equals(new Vector(new Point3D(1, 0, 0))));
        assertTrue(s1.getNormal(new Point3D(-4, 0, 0)).equals(new Vector(new Point3D(-1, 0, 0))));

        assertTrue(s2.getNormal(new Point3D(1, 1, 0)).equals(new Vector(new Point3D(0, 0, -1))));
        assertTrue(s2.getNormal(new Point3D(0, 1, 1)).equals(new Vector(new Point3D(-1, 0, 0))));
        assertTrue(s2.getNormal(new Point3D(1, 0, 1)).equals(new Vector(new Point3D(0, -1, 0))));

        //assertEquals(s1.getNormal(new Point3D(7, 7, 7)), null);


    }
}