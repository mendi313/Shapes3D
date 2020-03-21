package primitives;

import java.util.Objects;
import java.util.*;

public class Vector {
    private Point3D head;
    public static final Vector ZERO = new Vector(new Point3D(0.0, 0.0, 0.0));

    /***************contractors***********/
    /**
     * contractor for creating a Vector
     *
     * @param _x get x for the contractor
     * @param _y get y for the contractor
     * @param _z get z for the contractor
     */
    public Vector(Coordinate _x, Coordinate _y, Coordinate _z) throws IllegalArgumentException {
        if (_x.equals(0) && _y.equals(0) && _z.equals(0))
            throw new IllegalArgumentException("cannot except the zero vector");
        else
            head = new Point3D(_x, _y, _z);
    }

    /**
     * contractor double param
     *
     * @param _x get x for the contractor
     * @param _y get y for the contractor
     * @param _z get z for the contractor
     */
    public Vector(double _x, double _y, double _z) {
        if (_x == 0 && _y == 0 && _z == 0)
            throw new IllegalArgumentException("cannot except the zero Coordinates");
        else
            head = new Point3D(_x, _y, _z);
    }

    /**
     * contractor point param
     *
     * @param other get point3D for the contractor
     */
    public Vector(Point3D other) {
        if (other.getX().equals(0) && other.getY().equals(0) && other.getZ().equals(0))
            throw new IllegalArgumentException("cannot except the zero point");
        else
            this.head = other;
    }

    /**
     * contractor copy Vector
     *
     * @param other get vector for the copy contractor
     */
    public Vector(Vector other) {
        head = new Point3D(other.getHead());
    }

    /**
     * getter
     *
     * @return head
     */
    public Point3D getHead() {
        return head;
    }

    /**
     * Vector vector subtraction
     *
     * @param other get vector for the subtract
     * @return Vector
     */
    public Vector subtract(Vector other) {
        return new Vector(this.head.getX().get() - other.head.getX().get(),
                this.head.getY().get() - other.head.getY().get(),
                this.head.getZ().get() - other.head.getZ().get());
    }

    /**
     * adding vector to vector
     *
     * @param other get vector for the adding
     * @return vector
     */
    public Vector add(Vector other) {
        return new Vector(this.head.getX().get() + other.head.getX().get(),
                this.head.getY().get() + other.head.getY().get(),
                this.head.getZ().get() + other.head.getZ().get());
    }

    /************toString() and equals() ****/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
    }

    /**
     * scale vector in scalar
     *
     * @param scalar double
     * @return scale vector
     */
    public Vector scale(double scalar) {
        return new Vector(this.head.getX().get() * scalar, this.head.getY().get() * scalar, this.head.getZ().get() * scalar);
    }

    /**
     * @param other Vector
     * @return dotProduct(double)
     */
    public double dotProduct(Vector other) {
        return head.getX().get() * other.head.getX().get() +
                head.getY().get() * other.head.getY().get() +
                head.getZ().get() * other.head.getZ().get();
    }

    /**
     * @param other Vector
     * @return Vector for cross product(Vector)
     */
    public Vector crossProduct(Vector other) {
        return new Vector(this.head.getY().get() * other.head.getZ().get() -
                this.head.getZ().get() * other.head.getY().get(),
                this.head.getZ().get() * other.head.getX().get() -
                        this.head.getX().get() * other.head.getZ().get(),
                this.head.getX().get() * other.head.getY().get() -
                        this.head.getY().get() * other.head.getX().get()
        );
    }

    /**
     * squared the length of the vector
     *
     * @return double
     */
    public double lengthSquared() {
        double temp = this.head.getX().get() * this.head.getX().get() +
                this.head.getY().get() * this.head.getY().get() +
                this.head.getZ().get() * this.head.getZ().get();
        return temp;
    }

    /**
     * calculate the length of the vector
     *
     * @return double
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize() {
        double size = length();
        this.head = new Point3D(
                this.head.getX().get() / size,
                this.head.getY().get() / size,
                this.head.getZ().get() / size);
        return this;
    }

    public Vector normalized() {
        Vector v = new Vector(this);
        v.normalize();
        return v;
    }
}

