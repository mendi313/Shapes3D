package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Triangle class represents Euclidean 2D trinagle in 3D Cartesian coordinate
 * system represented by its 3 vertices
 */
public class Triangle extends Polygon {
    /**
     * Triangle constructor given its 3 vertices
     *
     * @param emissionLight the emission Light of the Triangle
     * @param material   the material of the Plane
     * @param p1 vertex 1
     * @param p2 vertex 2
     * @param p3 vertex 3
     */
    public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,material,p1,p2,p3);
    }
    /**
     * Triangle constructor given its 3 vertices
     *
     * @param emissionLight the emission Light of the Triangle
     * @param p1 vertex 1
     * @param p2 vertex 2
     * @param p3 vertex 3
     * @throws IllegalArgumentException if the points lay in the same line by any
     *                                  mean
     */
    public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
       this(emissionLight,new Material(0,0,0), p1, p2, p3);
    }
    /**
     * Triangle constructor given its 3 vertices
     * with default Values for the emissionLight
     *
     * @param p1 first point to build a triangle
     * @param p2 second point to build a triangle
     * @param p3 third point to build a triangle
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this(Color.BLACK, new Material(0,0,0), p1, p2, p3);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = _plane.findGeoIntersections(ray);
        if (intersections == null) return null;

        Point3D p0 = ray.getP0();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;
        intersections.get(0)._geometry = this;
        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;
    }
}
