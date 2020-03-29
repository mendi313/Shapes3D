import primitives.*;

import static java.lang.System.out;
import static primitives.Util.*;

/**
 * Submit the exercise:
 * 770mmw@gmail.com 313330284 menachem mendel weissman
 * efiroz95@gmail.com  315595272 efraim rozenblum
 * /**
 * Test program for the 1st stage
 *
 * @author Dan Zilberstein
 */
public final class Main {

    /**
     * Main program to tests initial functionality of the 1st stage
     *
     * @param args irrelevant here
     */
    public static void main(String[] args) {


        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);


        // test vector normalization vs vector length and cross-product
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

        out.println("If there were no any other outputs - all tests succeeded!");
    }
}
