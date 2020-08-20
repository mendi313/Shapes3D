package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.*;

/**
 * Interface for Composite Design Pattern The Composite Class - Geometries The
 * Basic Classes - all the specific geometries
 */
public interface Intersectable {
    /**
     * assistance class to know when we have intersection point to
     * witch geometries she belong
     */
    public static class GeoPoint {
        public Geometry _geometry;
        public Point3D _point;

        /***************Constructor***********/

        /**
         * Constructor for GeoPoint that get point and the geometry of the point
         *
         * @param geometry the geometry that crossed
         * @param point       the intersection point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            _geometry = geometry;
            _point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || !(o instanceof  GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return this._geometry.equals(geoPoint._geometry) &&
                    this._point.equals(geoPoint._point);
        }
    }
    /**
     * The functions looks for intersection points between a basic or a composite
     * geometry and a given ray. The function returns null if there are no
     * intersections
     *
     * @param ray the ray to intrsect a geometrie(s)
     * @return list of intersection points
     */
    default List<Point3D> findIntersections(Ray ray){
        List<GeoPoint> l1 = findGeoIntersections(ray);
        if(l1==null)return null;
        List<Point3D> l2 = new LinkedList<>();
        for (GeoPoint gp : l1)
            l2.add(gp._point);
        return l2;
    }
    /**
     * The functions looks for intersection points between a basic or a composite
     * geometry and a given ray. The function returns null if there are no
     * intersections
     *
     * @param ray the ray to intrsect a geometrie(s)
     * @return list of intersection points
     */
    List<GeoPoint> findGeoIntersections(Ray ray);
}
