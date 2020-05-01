package geometries;

import primitives.Point3D;
import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane implements Geometry {
    Point3D _p;
    Vector _normal;

    /***************contractors***********/

    /**
     * contractors that build the plane by 3 points
     *
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _p = new Point3D(p1);
        Vector U = new Vector(p1, p2);
        Vector V = new Vector(p1, p3);
        Vector N = U.crossProduct(V);
        N.normalize();
        _normal = N;
    }

    /**
     * contractors that build the plane by point and normal vector
     *
     * @param _p      first param
     * @param _normal second param
     */
    public Plane(Point3D _p, Vector _normal) {
        this._p = new Point3D(_p);
        this._normal = new Vector(_normal);
    }

    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    //because polygon

    /**
     * func that return normal to the plane
     *
     * @return normal vector
     */
    public Vector getNormal() {
        return getNormal(null);
    }

    /**
     * func to find the Intersections point between the ray and the Plane
     *
     * @param ray ray pointing toward a Geometry
     * @return List<Point3D> with the Intersections point
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector p0Q;
        try {
            p0Q = _p.subtract(ray.getPoint());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = _normal.dotProduct(ray.getDirection());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_normal.dotProduct(p0Q) / nv);

        return t <= 0 ? null : List.of(ray.getTargetPoint(t));
    }
}