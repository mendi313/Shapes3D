package primitives;

/**
 * Class Vector is the basic class representing a vector of Euclidean geometry
 * in Cartesian 3-Dimensional coordinate system.
 * @author Dan Zilberstein
 */
public class Vector {
    private Point3D _head;

    /**
     * Vector constructor by 3 coordinate values
     * @param x coordinate value
     * @param y coordinate value
     * @param z coordinate value
     * @throws IllegalArgumentException if all the coordinates are zero (or close to zero)
     */
    public Vector(double x, double y, double z) {
        _head = new Point3D(x, y, z);
        if (Point3D.ZERO.equals(_head))
            throw new IllegalArgumentException("Zero vector is not allowed");
    }

    /**
     * Vector constructor by head point
     * @param p head point
     * @throws IllegalArgumentException if all the coordinates are zero (or close to zero)
     */
    public Vector(Point3D p) {
        if (Point3D.ZERO.equals(p))
            throw new IllegalArgumentException("Zero vector is not allowed");
        _head = new Point3D(p);
    }

    /**
     * Vector constructor as a copy of a given vector
     * @param other vector to copy from
     */
    public Vector(Vector other) {
        _head = new Point3D(other._head);
    }

    /**
     * Header point getter
     * @return header point of the vector
     */
    public Point3D getHead() {
        return _head;
    }

    /**
     * Operation of addition of this vector and another vector
     *
     * @param other vector
     * @return new vector
     */
    public Vector add(Vector other) {
        return new Vector(_head._x._coord + other._head._x._coord, //
                _head._y._coord + other._head._y._coord, //
                _head._z._coord + other._head._z._coord);
    }

    /**
     * Calculates subtraction of a given vector (v&#x20D7;&#x2082;) from this
     * vector (v&#x20D7;&#x2081;)
     * @param other vector
     * @return new vector: v&#x20D7;&#x2081;-v&#x20D7;&#x2082;
     */
    public Vector subtract(Vector other) {
        return new Vector(_head._x._coord - other._head._x._coord, //
                _head._y._coord - other._head._y._coord, //
                _head._z._coord - other._head._z._coord);
    }

    /**
     * Calculates multiplying of this vector by a scalar <i>a</i>
     * @param scalar the scalar <i>a</i>
     * @return new vector: <i>a</i>&sdot;v&#x20D7;
     */
    public Vector scale(double scalar) {
        return new Vector(_head._x._coord * scalar, //
                _head._y._coord * scalar, //
                _head._z._coord * scalar);
    }

    /**
     * Calculates scalar vector multiplication (dot-Product) of this
     * (v&#x20D7;&#x2081;) and another (v&#x20D7;&#x2082;) vectors
     * @param other vector
     * @return number: v&#x20D7;&#x2081;&sdot;v&#x20D7;&#x2082;
     */
    public double dotProduct(Vector other) {
        return _head._x._coord * other._head._x._coord + _head._y._coord * other._head._y._coord
                + _head._z._coord * other._head._z._coord;
    }

    /**
     * Vector multiplication (cross-Product) of this (v&#x20D7;&#x2081;) and another
     * (v&#x20D7;&#x2082;) vectors
     * @param other vector
     * @return number: v&#x20D7;&#x2081;&#10799;v&#x20D7;&#x2082;
     */
    public Vector crossProduct(Vector other) {
        return new Vector(_head._y._coord * other._head._z._coord - _head._z._coord * other._head._y._coord, //
                _head._z._coord * other._head._x._coord - _head._x._coord * other._head._z._coord, //
                _head._x._coord * other._head._y._coord - _head._y._coord * other._head._x._coord);
    }

    /**
     * Calculate squared vector length
     *
     * @return |v&#x20D7;|&#xB2;
     */
    public double lengthSquared() {
        return _head._x._coord * _head._x._coord + //
                _head._y._coord * _head._y._coord + //
                _head._z._coord * _head._z._coord;
    }

    /**
     * Calculate vector length
     *
     * @return |v&#x20D7;|
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Create unit vector of the same direction (normalized vector)
     * @return new normalized vector
     */
    public Vector normalized() {
        double len = length();
        return new Vector(_head._x._coord / len, //
                _head._y._coord / len, //
                _head._z._coord / len);
    }

    /**
     * Make this vector to be unit vector (in the same direction)
     * @return this vector itself (normalized)
     */
    public Vector normalize() {
        double len = length();
        _head = new Point3D(_head._x._coord / len, //
                _head._y._coord / len, //
                _head._z._coord / len);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Vector))
            return false;
        return _head.equals(((Vector) obj)._head);
    }

    @Override
    public String toString() {
        return ">" + _head;
    }
}
