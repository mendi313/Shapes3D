package geometries;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

import java.util.Objects;

public class Sphere extends RadialGeometry {
    private Point3D _center;

    /**
     * contractor for creating a Sphere
     *
     * @param _radius get radios for contractor
     * @param _center get point3D for contractor
     */
    public Sphere(double _radius, Point3D _center) {
        super(_radius);
        this._center = new Point3D(_center);
    }

    /**
     * getter
     *
     * @return center point
     */
    public Point3D get_center() {
        return _center;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Sphere)) return false;
        Sphere other = (Sphere) o;
        return this._center.equals(other._center) && (Util.isZero(this._radius - other._radius));
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        Vector orthogonal = new Vector(point.subtract(_center));
        return orthogonal.normalized();
    }

}
