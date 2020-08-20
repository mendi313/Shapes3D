package primitives;

import static primitives.Util.*;

/**
 * Class Ray is the basic class representing a ray of Euclidean geometry
 * in Cartesian 3-Dimensional coordinate system.<br>
 * A ray consists of those points on a line passing through a given point and
 * proceeding indefinitely, starting at the given point, in one direction only along the line<br>
 * Ray direction is stored as a unit vector providing both line's direction and the part of the
 * line whose points belong to the ray
 *
 * @author Dan Zilberstein
 */
public class Ray {

    private static final double DELTA = 0.1;

    private Point3D _p0;
    private Vector _v;

    /**
     * Ray constructor by ray beginning point and its direction
     *
     * @param p ray beginning point
     * @param v ray direction vector
     */
    public Ray(Point3D p, Vector v) {
        _p0 = new Point3D(p);
        _v = v.normalized();
    }
    /**
     * Ray constructor by ray beginning point and its direction and DELTA
     *
     * @param p       ray beginning point
     * @param v       ray direction vector
     * @param normal  ray normal vector
     */
    public Ray(Point3D p, Vector v, Vector normal) {
        _v = v.normalized();
        double nv = normal.dotProduct(v);
        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        _p0 = p.add(normalDelta);
    }

    /**
     * Getter of ray beginning point
     * @return ray beginning point
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * Getter of ray direction
     *
     * @return direction vector (unit vector)
     */
    public Vector getDirection() {
        return _v;
    }

    /**
     * Get point on ray at a distance from ray's head
     *
     * @param t distance from ray head
     * @return the point
     */
    public Point3D getPoint(double t) {
        try {
            return _p0.add(_v.scale(t));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray oth = (Ray) obj;
        return _p0.equals(oth._p0) && _v.equals(oth._v);
    }

    @Override
    public String toString() {
        return "o" + _p0 + "-" + _v + ">";
    }
}
