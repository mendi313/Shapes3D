package primitives;

import java.math.*;
import java.util.Objects;

/**
 * Point3D: class for representing a point in 3D environment
 */
public class Point3D {
    private Coordinate x, y, z;
    public static final Point3D ZERO = new Point3D(0.0, 0.0, 0.0);

    /***************contractors***********/
    /**
     * contractor for creating a point
     *
     * @param _x get x for the contractor
     * @param _y get y for the contractor
     * @param _z get z for the contractor
     */
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
    }

    /**
     * contractor double param
     *
     * @param _x get x for the contractor
     * @param _y get y for the contractor
     * @param _z get z for the contractor
     */
    public Point3D(double _x, double _y, double _z) {
        this(new Coordinate(_x), new Coordinate(_y), new Coordinate(_z));
    }

    /**
     * contractor copy point
     *
     * @param other get point3D for the contractor
     */
    public Point3D(Point3D other) {
        this.x = new Coordinate(other.x);
        this.y = new Coordinate(other.y);
        this.z = new Coordinate(other.z);

    }
    /****************getters*************/
    /**
     * get x
     *
     * @return x
     */
    public Coordinate getX() {
        return new Coordinate(x);
    }

    /**
     * get y
     *
     * @return y
     */
    public Coordinate getY() {
        return new Coordinate(y);
    }

    /**
     * get z
     *
     * @return z
     */
    public Coordinate getZ() {
        return new Coordinate(z);
    }

    /**
     * calculate vector between point to point
     *
     * @param v get Vector for the subtract
     * @return Point3D
     */
    public Point3D subtract(Vector v) {
        return new Point3D(this.x._coord - v.getHead().x._coord,
                this.y._coord - v.getHead().y._coord,
                this.z._coord - v.getHead().z._coord);
    }

    /**
     * calculate vector between point to point
     *
     * @param other get point3D for the subtract
     * @return Vector
     */
    public Vector subtract(Point3D other) {
        return new Vector(x.get() - other.getX().get(), y.get() - other.getY().get(), z.get() - other.getZ().get());
    }

    /**
     * adding vector to point
     *
     * @param other get point3D for the adding
     * @return Point3D
     */
    public Point3D add(Vector other) {
        Point3D temp = other.getHead();
        return new Point3D(x.get() + temp.getX().get(), y.get() + temp.getY().get(), z.get() + temp.getZ().get());
    }

    /**
     * calculate squared distance between points
     *
     * @param other get point3D for the calculate distanceSquared
     * @return double
     */
    public double distanceSquared(Point3D other) {
        double _x = other.getX().get() - x.get();
        double _y = other.getY().get() - y.get();
        double _z = other.getZ().get() - z.get();
        return _x * _x + _y * _y + _z * _z;
    }

    /**
     * calculate distance between points
     *
     * @param other get point3D for the calculate distance
     * @return double
     */
    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }

    /************toString() and equals() ****/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return x.equals(point3D.x) &&
                y.equals(point3D.y) &&
                z.equals(point3D.z);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")/n";
    }
}