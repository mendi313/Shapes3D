package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {
    private double _height;

    public Cylinder(double _radius, Point3D _center, Vector _direction, double _height) {
        super(_radius, _center, _direction);
        this._height = _height;
    }

    public double get_height() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        Vector u = new Vector(point.subtract(get_center()));
        double t = get_direction().dotProduct(u);
        Point3D p = get_center().add(get_direction().scale(t));
        Vector n = new Vector(point.subtract(p));
        return n.normalize();
    }
}
