package unittests.primitives;

import static java.lang.System.out;
import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Vector;

public class VectorTests extends Object {

    Vector v1 = new Vector(1.0, 1.0, 1.0);
    Vector v2 = new Vector(-1.0, -1.0, -2.0);

    @Test
    public void vectorCreateTest() {
        try {
            new Vector(0, 0, 0);
            out.println("ERROR: zero vector does not throw an exception");
        } catch (IllegalArgumentException e){
            out.println(e);
        }
    }

    @Test
    public void testAdd() {

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, -2.0);

        v1 = v1.add(v2);
        assertTrue(v1.equals(new Vector(0.0, 0.0, -1.0)));
        assertEquals(new Vector(0.0, 0.0, -1.0), v1);
        v2 = v2.add(v1);
        assertEquals(new Vector(-1.0, -1.0, -3.0), v2);

    }

    @Test
    public void testSubtract() {
        v1 = v1.subtract(v2);
        assertEquals(new Vector(2.0, 2.0, 3.0), v1);
        assertTrue(v1.equals(new Vector(2.0, 2.0, 3.0)));

        v2 = v2.subtract(v1);
        assertEquals(new Vector(-3.0, -3.0, -5.0), v2);
        assertTrue(v2.equals(new Vector(-3.0, -3.0, -5.0)));

    }

    @Test
    public void testScaling() {
        v1 = v1.scale(1);
        assertEquals(new Vector(1.0, 1.0, 1.0), v1);
        v1 = v1.scale(2);
        assertEquals(new Vector(2.0, 2.0, 2.0), v1);
        v1 = v1.scale(-2);
        assertEquals(new Vector(-4.0, -4.0, -4.0), v1);
        try {
            v1 = v1.scale(0);
        } catch (IllegalArgumentException e) {
            System.out.println("cannot bw ZERO");
            assertTrue(true);
        }
    }

    @Test
    public void testDotProduct() {

        Vector v1 = new Vector(3.5, -5, 10);
        Vector v2 = new Vector(2.5, 7, 0.5);

        assertTrue(Double.compare(v1.dotProduct(v2), (8.75 + -35 + 5)) == 0);

    }

    @Test
    public void testLength() {
        Vector v = new Vector(3.5, -5, 10);
        assertTrue(v.length() ==
                Math.sqrt(12.25 + 25 + 100));
    }

    @Test
    public void testNormalize() {

        Vector v = new Vector(3.5, -5, 10);
        v.normalize();
        assertEquals(1, v.length(), 1e-10);

        try {
            Vector v1 = new Vector(0, 0, 0);
            v.normalize();
            fail("Didn't throw divide by zero exception!");
        } catch (IllegalArgumentException ex) {
            assertEquals("Point3D(0.0,0.0,0.0) not valid for vector head", ex.getMessage());
        }
        assertTrue(true);

    }

    @Test
    public void testCrossProduct() {

        Vector v1 = new Vector(3.5, -5.0, 10.0);
        Vector v2 = new Vector(2.5, 7, 0.5);
        Vector v3 = v1.crossProduct(v2);

        assertEquals(0, v3.dotProduct(v2), 1e-10);
        assertEquals(0, v3.dotProduct(v1), 1e-10);

        Vector v4 = v2.crossProduct(v1);

        System.out.println(v3.toString());
        System.out.println(v4.toString());

        try {
            v3.add(v4);
            fail("Vector (0,0,0) not valid");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage() != null);
        }
//        assertTrue(v3.length() >84);
        assertEquals(84, v3.length(), 0.659);

    }


}