package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;


public class Tube extends RadialGeometry {
    /**
     * Represents the direction of the tube
     */
    private final Vector _direction;

    /**
     * A center point in the tube
     */
    private final Point3D _center;

    /**
     * constructor for a new Cylinder object
     *
     * @param _radius    the radius of the cylinder
     * @param _direction the direction of the cylinder
     * @throws Exception in case of a negative radius
     */
    public Tube(double _radius, Point3D _center, Vector _direction) {
        super(_radius);
        this._center = new Point3D(_center);
        this._direction = _direction.normalized();
    }

    /**
     * copy constructor for a tube object to be deep copied
     *
     * @param other the source parameter
     */
    public Tube(Tube other) {
        super(other);
        this._center = new Point3D(other._center);
        this._direction = other._direction.normalized();
    }

    public Vector get_direction() {
        return new Vector(_direction);
    }

    public Point3D get_center() {
        return new Point3D(_center);
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
            Vector v = _direction.crossProduct(other._direction);
        } catch (IllegalArgumentException ex) {
            return (Util.isZero(this._radius - other._radius) && _center.equals((other._center)));
        }
        throw new IllegalArgumentException("direction cross product with parameter.direction == Vector(0,0,0)");
    }

    @Override
    public String toString() {
        return "point: " + _center +
                " direction: " + _direction +
                ", radius: " + _radius;
    }

    @Override
    public Vector getNormal(Point3D point) {
        //The vector from the point of the cylinder to the given point
        Vector vector1 = point.subtract(_center);

        //We need the projection to multiply the _direction unit vector
        double projection = vector1.dotProduct(_direction);

        Vector vector2 = _direction.scale(projection);

        //This vector is orthogonal to the _direction vector.
        Vector check = vector1.subtract(vector2);
        return check.normalize();
    }
}
