package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;

public class Sphere extends RadialGeometry {
    private Point3D _center;

    /**
     * contractor for creating a Sphere
     *
     * @param _radius first param
     * @param _center second param
     */
    public Sphere(double _radius, Point3D _center) {
        super(_radius);
        this._center = new Point3D(_center);
    }

    /**
     * get_center
     *
     * @return center point
     */
    public Point3D get_center() {
        return new Point3D(_center);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Sphere)) return false;
        Sphere other = (Sphere) o;
        return this._center.equals(other._center) && (Util.isZero(this._radius - other._radius));
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        Vector orthogonal = new Vector(point.subtract(_center));
        return orthogonal.normalized();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(ray.getTargetPoint(_radius));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getTargetPoint(t1));
        else
            return List.of(ray.getTargetPoint(t2));
    }
}
