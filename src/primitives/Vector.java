package primitives;

import java.util.Objects;
import java.util.*;

public class Vector {
    private Point3D head;

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
        this(new Point3D(_x, _y, _z));
    }

    public Vector(Point3D p1, Point3D p2) {
        this(p1.subtract(p2));
    }

    /**
     * contractor point param
     *
     * @param p get point3D for the contractor
     */
    public Vector(Point3D p) {
        if (p.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("Point3D(0.0,0.0,0.0) not valid for vector head");
        }
        this.head = new Point3D(p.getX()._coord, p.getY()._coord, p.getZ()._coord);
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
     * getter for the head point
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
        return head.toString();
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
     * func that calculate dotProduct between 2 vectors
     *
     * @param other Vector to dotProduct
     * @return double
     */
    public double dotProduct(Vector other) {
        return head.getX().get() * other.head.getX().get() +
                head.getY().get() * other.head.getY().get() +
                head.getZ().get() * other.head.getZ().get();
    }

    /**
     * func that calculate crossProduct between 2 vectors
     *
     * @param other Vector to crossProduct
     * @return Vector cross product with (Vector other)
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

    /**
     * func to normalize our vector
     *
     * @return normalize vector
     */
    public Vector normalize() {
        double length = this.length();
        if (length == 0)
            throw new ArithmeticException("divide by ZERO");
        this.head = new Point3D(
                this.head.getX().get() / length,
                this.head.getY().get() / length,
                this.head.getZ().get() / length);
        return this;
    }

    /**
     * func to normalize vector
     *
     * @return normalize vector
     */
    public Vector normalized() {
        Vector v = new Vector(this);
        v.normalize();
        return v;
    }

}

