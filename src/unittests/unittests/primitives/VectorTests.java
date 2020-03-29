package unittests.primitives;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.Vector;

public class VectorTests extends Object {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    @Test
    public void vectorCreateTest() {
        try {
            new Vector(0, 0, 0);
            out.println("ERROR: zero vector does not throw an exception");
        } catch (IllegalArgumentException e) {
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
        assertEquals(new Vector(3.0, 6.0, 9.0), v1);
        v2 = v2.subtract(v1);
        assertEquals(new Vector(-5.0, -10.0, -15.0), v2);
    }

    @Test
    public void testScaling() {
        v1 = v1.scale(1);
        assertEquals(new Vector(1.0,2.0,3.0), v1);
        v1 = v1.scale(2);
        assertEquals(new Vector(2.0,4.0,6.0), v1);
        v1 = v1.scale(-2);
        assertEquals(new Vector(-4.0,-8.0,-12.0), v1);
        try {
            v1 = v1.scale(0);
        } catch (IllegalArgumentException e) {
            System.out.println("cannot bw ZERO");
            assertTrue(true);
        }
    }

    @Test
    public void testDotProduct() {

        if (!isZero(v1.dotProduct(v3)))
            out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            out.println("ERROR: dotProduct() wrong value");

    }

    @Test
    public void testLength() {
        if (!isZero(v1.lengthSquared() - 14))
            out.println("ERROR: lengthSquared() wrong value");
        if (!isZero(new Vector(0, 3, 4).length() - 5))
            out.println("ERROR: length() wrong value");
    }

    @Test
    public void testNormalize() {

        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        if (vCopy != vCopyNormalize)
            out.println("ERROR: normalize() function creates a new vector");
        if (!isZero(vCopyNormalize.length() - 1))
            out.println("ERROR: normalize() result is not a unit vector");
        Vector u = v.normalized();
        if (u == v)
            out.println("ERROR: normalizated() function does not create a new vector");

    }

    @Test
    public void testCrossProduct() {

        try { // test zero vector
            v1.crossProduct(v2);
            out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }
        Vector vr = v1.crossProduct(v3);
        if (!isZero(vr.length() - v1.length() * v3.length()))
            out.println("ERROR: crossProduct() wrong result length");
        if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
            out.println("ERROR: crossProduct() result is not orthogonal to its operands");

    }


}