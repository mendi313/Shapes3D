package geometries;

import primitives.*;
import static primitives.Util.*;
import java.util.List;

/**
 * Cylinder class represents Euclidean cylinder in 3D Cartesian coordinate
 * system represented by its central ray beginning at its base, its radius and
 * its height
 */
public class Cylinder extends Tube {
    private double _height;
    private Plane _base1;
    private Plane _base2;

    /**
     * Cylinder constructor given its central ray beginning at its base, radius and
     * height
     * @param emissionLight the emission Light of the Tube
     * @param material   the material of the Plane
     * @param radius is the radius
     * @param r is the ray
     * @param height the height of the Cylinder
     */
    public Cylinder(Color emissionLight, Material material, double radius, Ray r, double height) {
        super(emissionLight, material, radius, r);
        _height = height;

        Vector v = r.getDirection();
        _base1 = new Plane(r.getP0(), v);
        _base2 = new Plane(r.getPoint(height), v);
    }
    /**
     * Cylinder constructor given its central ray beginning at its base, radius and
     * height
     * @param emissionLight the emission Light of the Tube
     * @param radius is the radius of the cylinder
     * @param r is the ray
     * @param height the height of the Cylinder
     */
    public Cylinder(Color emissionLight,double radius, Ray r, double height) {
        this(emissionLight, new Material(0, 0, 0), radius, r, height);
    }
    /**
     * Cylinder constructor given its central ray beginning at its base, radius and
     * height
     * @param radius is the radius of the cylinder
     * @param r is the ray
     * @param height the height of the Cylinder
     */
    public Cylinder(double radius, Ray r, double height) {
        this(Color.BLACK, new Material(0, 0, 0), radius, r, height);
    }
    /**
     * Height getter
     *
     * @return height value
     */
    double getHeight() { return _height; }

    @Override
    public Vector getNormal(Point3D point) {
        Point3D o = _axis.getP0();
        Vector v = _axis.getDirection();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(point.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return point.subtract(o).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        Vector vAxis = _axis.getDirection();
        Vector v = ray.getDirection();
        Point3D p0 = ray.getP0();
        Point3D pC = _axis.getP0();
        Point3D p1;
        Point3D p2;

        // intersections of the ray with infinite cylinder {without the bases)
        List<GeoPoint> intersections = super.findGeoIntersections(ray);
        double vAxisV = alignZero(vAxis.dotProduct(v)); // cos(angle between ray directions)

        if (intersections == null) { // no intersections with the infinite cylinder
            try {
                vAxis.crossProduct(v); // if ray and axis are parallel - throw exception
                return null; // they are not parallel - the ray is outside the cylinder
            } catch (Exception e) {}
            // The rays are parallel
            Vector vP0PC;
            try {
                vP0PC = pC.subtract(p0); // vector fro P0 to Pc (O1)
            } catch (Exception e) { // the rays start at the same point
                // check whether the ray goes into the cylinder
                return vAxisV > 0 ? List.of(new GeoPoint(this,ray.getPoint(_height))) : null;
            }

            double t1 = alignZero(vP0PC.dotProduct(v)); // project Pc (O1) on the ray
            p1 = ray.getPoint(t1); // intersection point with base1

            // Check the distance between the rays
            if (alignZero(p1.distance(pC) - _radius) >= 0) return null;

            // intersection points with base2
            double t2 = alignZero(vAxisV > 0 ? t1 + _height : t1 - _height);
            p2 = ray.getPoint(t2);
            // the ray goes through the bases - test bases vs. ray head and return points accordingly
            if (t1 > 0 && t2 > 0) return List.of(new GeoPoint(this,p1),new GeoPoint(this, p2));
            if (t1 > 0) return List.of(new GeoPoint(this,p1));
            if (t2 > 0) return List.of(new GeoPoint(this,p2));
            return null;
        }

        // Ray - infinite cylinder intersection points
        p1 = intersections.get(0)._point;
        p2 = intersections.get(1)._point;

        // get projection of the points on the axis vs. base1 and update the
        // points if both under base1 or they are from different sides of base1
        double p1OnAxis = alignZero(p1.subtract(pC).dotProduct(vAxis));
        double p2OnAxis = alignZero(p2.subtract(pC).dotProduct(vAxis));
        if (p1OnAxis < 0 && p2OnAxis < 0) p1 = null;
        else if (p1OnAxis < 0) p1 = _base1.findGeoIntersections(ray).get(0)._point;
        else /* p2OnAxis < 0 */ p2 = _base1.findGeoIntersections(ray).get(0)._point;

        // get projection of the points on the axis vs. base2 and update the
        // points if both above base2 or they are from different sides of base2
        double p1OnAxisMinusH = alignZero(p1OnAxis - _height);
        double p2OnAxisMinusH = alignZero(p2OnAxis - _height);
        if (p1OnAxisMinusH > 0 && p2OnAxisMinusH > 0) p1 = null;
        else if (p1OnAxisMinusH > 0) p1 = _base2.findGeoIntersections(ray).get(0)._point;
        else /* p2OnAxisMinusH > 0 */ p2 = _base2.findGeoIntersections(ray).get(0)._point;

        // Check the points and return list of points accordingly
        if (p1 != null && p2 != null) return List.of(new GeoPoint(this,p1),new GeoPoint(this, p2));
        if (p1 != null) return List.of(new GeoPoint(this,p1));
        if (p2 != null) return List.of(new GeoPoint(this,p2));
        return null;
    }
}

