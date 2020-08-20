package geometries;

import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Sphere class represents Euclidean sphere in 3D Cartesian coordinate system
 * represented by its center (point) and radius
 */
public class Sphere extends RadialGeometry {
    /**
     * Sphere center
     */
    Point3D _o;

    /**
     * Sphere constructor by it's center and radius
     *
     * @param emissionLight the emissionLight of the Sphere
     * @param material      the material of the Plane
     * @param radius is the radius
     * @param center is the center
     */
    public Sphere(Color emissionLight, Material material, double radius, Point3D center) {
        super(emissionLight, material, radius);
        _o = new Point3D(center);
    }

    /**
     * constructor for creating a Sphere with default Values for the kd, ks and nShininess
     *
     * @param emissionLight the emissionLight of the Sphere
     * @param radius is the radius
     * @param center is the center
     */
    public Sphere(Color emissionLight, double radius, Point3D center) {
        this(emissionLight, new Material(0, 0, 0), radius, center);
    }

    /**
     * constructor for creating a Sphere with default Values for the kd, ks, emissionLight, and nShininess
     *
     * @param _radius is the radius
     * @param _center is the center
     */
    public Sphere(double _radius, Point3D _center) {
        this(Color.BLACK, new Material(0, 0, 0), _radius, _center);
    }

    @Override
    public Vector getNormal(Point3D point) {
        // Create vector by connecting from the center to the point and getting unit
        // vector
        return point.subtract(_o).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        Point3D p0 = ray.getP0();
        Vector v = ray.getDirection();
        Vector u;
        try {
            u = _o.subtract(p0);
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, ray.getPoint(_radius)));
        }

        double tm = v.dotProduct(u);
        double dSquared = isZero(tm) ? u.lengthSquared() : u.lengthSquared() - tm * tm;

        double thSquared = alignZero(_radius * _radius - dSquared);
        if (thSquared <= 0) return null;
        double th = Math.sqrt(thSquared);
        if (isZero(th)) return null;

        double t1 = alignZero(tm + th);
        if (t1 <= 0) return null; // since th must be positive (sqrt), t2 must be non-positive as t1
        GeoPoint gp1 = new GeoPoint(this, ray.getPoint(t1));

        double t2 = alignZero(tm - th);
        // if both t1 and t2 are positive - there are two points
        GeoPoint gp2 =  t2 <= 0 ? null : new GeoPoint(this, ray.getPoint(t2));

        if (gp2 == null)
            return gp1 == null ? null : List.of(gp1);
        return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
    }
}
