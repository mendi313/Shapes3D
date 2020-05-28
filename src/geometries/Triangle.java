package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

public class Triangle extends Polygon {

    /***************contractors***********/

    /**
     * contractor for creating a using tje father polygon
     *
     * @param p1 first point to build a triangle
     * @param p2 second point to build a triangle
     * @param p3 third point to build a triangle
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    /**
     * constructor for a new Triangle object with default Values for the kd, ks, and nShininess
     *
     * @param emissionLight the emissionLight of the triangle
     * @param p1            first point to build a triangle
     * @param p2            second point to build a triangle
     * @param p3            third point to build a triangle
     */
    public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight, p1, p2, p3);
    }

    /**
     * * constructor for a new Triangle object
     *
     * @param emissionLight the emissionLight of the triangle
     * @param material      the attenuation
     * @param p1            first point to build a triangle
     * @param p2            second point to build a triangle
     * @param p3            third point to build a triangle
     */
    public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight, material, p1, p2, p3);
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

    /**
     * func to find the Intersections point between the ray and the Triangle
     *
     * @param ray ray pointing toward a Geometry
     * @return List<GeoPoint> with the Intersections point
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> planeIntersections = _plane.findIntersections(ray, maxDistance);
        if (planeIntersections == null) return null;

        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0).normalized();
        Vector v2 = _vertices.get(1).subtract(p0).normalized();
        Vector v3 = _vertices.get(2).subtract(p0).normalized();

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            //for GeoPoint
            for (GeoPoint geo : planeIntersections) {
                geo._geometry = this;
            }
            return planeIntersections;
        }

        return null;

    }
}
