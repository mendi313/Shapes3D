package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon {
    /**
     * contractor for creating a Triangle
     *
     * @param p1 first param
     * @param p2 second param
     * @param p3 third param
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Triangle)) return false;
        Triangle tr = (Triangle) obj;
        return _vertices.get(0).equals(tr._vertices.get(0)) &&
                _vertices.get(1).equals(tr._vertices.get(1)) &&
                _vertices.get(2).equals(tr._vertices.get(2));
    }

    @Override
    public String toString() {
        String result = "";
        for (Point3D p : _vertices) {
            result += p.toString();
        }
        return result;
    }

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        return super.findIntsersections(ray);
    }
}
