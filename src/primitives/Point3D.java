package primitives;

/**
 * Class Point3D is the basic class representing a point of Euclidean geometry
 * in Cartesian 3-Dimensional coordinate system.
 */
public class Point3D {
    /**
     * x coordinate, intentionally "package-friendly"
     */
    final Coordinate _x;
    /**
     * y coordinate, intentionally "package-friendly"
     */
    final Coordinate _y;
    /**
     * z coordinate, intentionally "package-friendly"
     */
    final Coordinate _z;

    /**
     * Point3D.ZERO - reference point of coordinate system (0,0,0)
     */
    public static Point3D ZERO = new Point3D(0.0, 0.0, 0.0);

    /**
     * 3D point constructor by x, y and z coordinate values
     *
     * @param x - coordinate value
     * @param y - coordinate value
     * @param z - coordinate value
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     * Constructor for creating a copy of a given point
     *
     * @param other point instance to copy from
     */
    public Point3D(Point3D other) {
        _x = new Coordinate(other._x);
        _y = new Coordinate(other._y);
        _z = new Coordinate(other._z);
    }

    /**
     * x coordinate value getter
     *
     * @return value of the coordinate
     */
    public double getX() {
        return _x._coord;
    }

    /**
     * y coordinate value getter
     *
     * @return value of the coordinate
     */
    public double getY() {
        return _y._coord;
    }

    /**
     * z coordinate value getter
     *
     * @return value of the coordinate
     */
    public double getZ() {
        return _z._coord;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Point3D))
            return false;
        Point3D oth = (Point3D) obj;
        return _x.equals(oth._x) && _y.equals(oth._y) && _z.equals(oth._z);
    }

    @Override
    public String toString() {
        return "(" + _x + "," + _y + "," + _z + ")";
    }

    /**
     * Adds a given vector v&#x20D7; to this point (P&#x2081;) resulting in a new point
     * @param v vector to be added
     * @return new point P&#x2081;+v&#x20D7;
     */
    public Point3D add(Vector v) {
        Point3D other = v.getHead();
        return new Point3D(_x._coord + other._x._coord, //
                _y._coord + other._y._coord, //
                _z._coord + other._z._coord);
    }

    /**
     * Subtracts a point (P&#x2082;) from this point (P&#x2081;) resulting in a
     * vector from a given point to this point
     * @param other point to be subtracted (P&#x2082;)
     * @return the result vector of P&#x2081;-P&#x2082;
     */
    public Vector subtract(Point3D other) {
        return new Vector(_x._coord - other._x._coord, //
                _y._coord - other._y._coord, //
                _z._coord - other._z._coord);
    }

    /**
     * Calculates squared distance between this point (P&#x2081;) and
     * another point (P&#x2082;)
     * @param other point
     * @return |P&#x2081;P&#x2082;|&#xB2;
     */
    public double distanceSquared(Point3D other) {
        double dx = _x._coord - other._x._coord;
        double dy = _y._coord - other._y._coord;
        double dz = _z._coord - other._z._coord;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Calculates distance between this point (P&#x2081;) and
     * another point (P&#x2082;)
     * @param other point
     * @return |P&#x2081;P&#x2082;|
     */
    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }
}
