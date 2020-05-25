package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;

public class Sphere extends RadialGeometry {
    private Point3D _center;

    /***************contractors***********/

    /**
     * contractor for creating a Sphere
     *
     * @param emissionLight the emissionLight of the Sphere
     * @param material      the attenuation
     * @param radius        the radius of the sphere
     * @param center        the point center of the sphere
     */
    public Sphere(Color emissionLight, Material material, double radius, Point3D center) {
        super(emissionLight, radius);
        this._material = material;
        this._center = new Point3D(center);
    }

    /**
     * contractor for creating a Sphere with default Values for the kd, ks and nShininess
     *
     * @param emissionLight the emissionLight of the Sphere
     * @param radius        first param
     * @param center        second param
     */
    public Sphere(Color emissionLight, double radius, Point3D center) {
        this(emissionLight, new Material(0, 0, 0), radius, center);
    }

    /**
     * contractor for creating a Sphere with default Values for the kd, ks, emissionLight, and nShininess
     *
     * @param _radius first param
     * @param _center second param
     */
    public Sphere(double _radius, Point3D _center) {
        this(Color.BLACK, new Material(0, 0, 0), _radius, _center);
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

    /**
     * func to calculate the normal vector
     *
     * @param point the point to calculate from her
     * @return Normal vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector orthogonal = new Vector(point.subtract(_center));
        return orthogonal.normalized();
    }

    /**
     * func to find the Intersections point between the ray and the Sphere
     *
     * @param ray ray pointing toward a Geometry
     * @return List<GeoPoint> with the Intersections point
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, (ray.getTargetPoint(this._radius))));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(this._radius * this._radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        double t1dist = alignZero(maxDistance - t1);
        double t2dist = alignZero(maxDistance - t2);

        if (t1 <= 0 && t2 <= 0) {
            return null;
        }

        if (t1 > 0 && t2 > 0) {
            if (t1dist > 0 && t2dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getTargetPoint(t1))),
                        new GeoPoint(this, (ray.getTargetPoint(t2)))); //P1 , P2
            } else if (t1dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getTargetPoint(t1))));
            } else if (t2dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getTargetPoint(t2))));
            }
        }

        if ((t1 > 0) && (t1dist > 0))
            return List.of(new GeoPoint(this, (ray.getTargetPoint(t1))));
        else if ((t2 > 0) && (t2dist > 0))
            return List.of(new GeoPoint(this, (ray.getTargetPoint(t2))));
        return null;
    }
}
