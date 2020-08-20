package geometries;

import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Plane class represents Euclidean plane in 3D Cartesian coordinate system
 * represented by a point in the plane and normalized vector orthogonal to the
 * plane (the normal)
 */
public class Plane extends Geometry {

    private Point3D _q;
    protected Vector _n;

    /**
     * Plane constructor given three points in the plane. The constructor will
     * calculate normal to the plane and take the 1st point as a point in the plane
     * If some of points' pair are the same or all the points are in the same line -
     * there will be an exception
     *
     * @param emissionLight the emissionLight of the Plane
     * @param material      the material of the Plane
     * @param p1            1st point
     * @param p2            2nd point
     * @param p3            3rd point
     * @throws IllegalArgumentException if the points are on the same line by any
     *                                  constellation
     */
    public Plane(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight, material);
        // if p1=p2 or p1=p3 - an exception will be thrown
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);

        // if the points are in the same line - X-product will throw an exception
        _n = v1.crossProduct(v2).normalize();
        _q = new Point3D(p1);
    }

    /**
     * contractors that build the plane by 3 points,
     * with default Values for the kd, ks, and nShininess.
     *
     * @param emissionLight the emissionLight of the Plane
     * @param p1            first point for creating a Plane
     * @param p2            second point for creating a Plane
     * @param p3            third point for creating a Plane
     */
    public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        this(emissionLight, new Material(0, 0, 0), p1, p2, p3);
    }

    /**
     * contractors that build the plane by 3 points,
     * with default Values for the kd, ks, emissionLight, and nShininess.
     *
     * @param p1 first point for creating a Plane
     * @param p2 second point for creating a Plane
     * @param p3 third point for creating a Plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this(Color.BLACK, new Material(0, 0, 0), p1, p2, p3);
    }

    /**
     * Plane constructor given a reference point in the plane and a vector
     * orthogonal to the plane. The vector will be normalized (made a unit vector)
     *
     * @param p      is the point
     * @param n      is the normal
     */
    public Plane(Point3D p, Vector n) {
        super(Color.BLACK, new Material(0, 0, 0));
        _n = n.normalized();
        _q = new Point3D(p);
    }

    /**
     * Plane constructor given a reference point in the plane and a vector
     * orthogonal to the plane. The vector will be normalized (made a unit vector)
     *
     * @param emissionLight   the emissionLight of the Plane
     * @param material        the material of the Plane
     * @param p               is the point
     * @param n               is the normal
     */
    public Plane(Color emissionLight, Material material,Point3D p, Vector n ) {
        super(emissionLight, material);
        _n = n.normalized();
        _q = new Point3D(p);
    }

    /**
     * Reference point getter
     *
     * @return reference point
     */
    public Point3D getPoint() {
        return _q;
    }

    /**
     * Normal vector getter
     *
     * @return unit vector orthogonal to the plane
     */
    public Vector getNormal() {
        return _n;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return _n;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        Vector p0Q;
        try {
            p0Q = _q.subtract(ray.getP0());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = _n.dotProduct(ray.getDirection());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_n.dotProduct(p0Q) / nv);
        return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
    }
}
