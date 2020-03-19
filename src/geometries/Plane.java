package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {
    Point3D point;
    Vector normal;

    public Plane(Point3D point) {
        this.point = point;
    }

    public Plane(Point3D vertex, Point3D vertex1, Point3D vertex2) {
    }

    public Plane(Vector normal) {
        this.normal = normal;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return Vector.ZERO;
    }

    public Vector getNormal() {
        return getNormal(new Point3D(0.0,0.0,0.0));
    }
}
