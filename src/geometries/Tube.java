package geometries;

import primitives.*;

import java.util.List;

import static java.lang.StrictMath.sqrt;
import static primitives.Util.isZero;

/**
 * Represents an infinite tube in the 3D space.
 * That is, the cylinder does not have a length.
 */

public class Tube extends RadialGeometry {
    /**
     * represents the direction and the reference point
     */
    protected final Ray _ray;

    /**
     * constructor for a new Cylinder object
     *
     * @param _radius the radius of the cylinder
     * @param _ray    the direction of the cylinder from a center point
     */
    public Tube(double _radius, Ray _ray) {
        super(_radius);
        this._ray = new Ray(_ray);
    }

    /**
     * copy constructor for a tube object to be deep copied
     *
     * @param other the source parameter
     */
    public Tube(Tube other) {
        super(other);
        this._ray = new Ray(other._ray);
    }

    /**
     * @return ray
     */
    public Ray get_ray() {
        return new Ray(_ray);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Tube))
            return false;
        if (this == obj)
            return true;
        Tube other = (Tube) obj;

        //the two vectors needs to be in the same direction,
        //but not necessary to have the same length.
        try {
            Vector v = _ray.getDirection().crossProduct(other._ray.getDirection());
        } catch (IllegalArgumentException ex) {
            return (Util.isZero(this._radius - other._radius) && _ray.getPoint().equals((_ray.getPoint())));
        }
        throw new IllegalArgumentException("direction cross product with parameter.direction == Vector(0,0,0)");
    }

    @Override
    public String toString() {
        return "ray: " + _ray +
                ", radius: " + _radius;
    }

    /**
     * @param point point to calculate the normal
     * @return returns normal vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        //The vector from the point of the cylinder to the given point
        Point3D o = _ray.getPoint(); // at this point o = p0
        Vector v = _ray.getDirection();

        Vector vector1 = point.subtract(o);

        //We need the projection to multiply the _direction unit vector
        double projection = vector1.dotProduct(v);
        if (!isZero(projection)) {
            // projection of P-O on the ray:
            o = o.add(v.scale(projection));
        }

        //This vector is orthogonal to the _direction vector.
        Vector check = point.subtract(o);
        return check.normalize();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {

        Vector AB = _ray.getDirection();
        Vector AO = ray.getPoint().subtract(_ray.getPoint());
        Vector AOxAB = AO.crossProduct(AB);
        Vector VxAB = ray.getDirection().crossProduct(AB);
        double ab2 = AB.dotProduct(AB);
        double a = VxAB.dotProduct(VxAB);
        double b = 2 * VxAB.dotProduct(AOxAB);
        double c = AOxAB.dotProduct(AOxAB) - (_radius * _radius * ab2);
        double d = b * b - 4 * a * c;
        if (d < 0)
            return null;
        double t1 = (-b - sqrt(d)) / (2 * a);
        double t2 = (-b + sqrt(d)) / (2 * a);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getTargetPoint(t1));
        else
            return List.of(ray.getTargetPoint(t2));
    }
}