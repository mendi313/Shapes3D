package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public interface Intersectable {
    /**
     * assistance class to know when we have intersection point to
     * witch geometries she belong
     */
    public static class GeoPoint {

        Geometry _geometry;
        Point3D point;

        /***************contractors***********/

        /**
         * contractors for GeoPoint that get point and the geometry of the point
         *
         * @param _geometry the geometry that crossed
         * @param pt        the intersection point
         */
        public GeoPoint(Geometry _geometry, Point3D pt) {
            this._geometry = _geometry;
            point = pt;
        }

        /**************getters**************/

        /**
         * getter for the intersection point
         *
         * @return new point3D
         */
        public Point3D getPoint() {
            return new Point3D(point);
        }

        /**
         * getter for the geometry
         *
         * @return geometry type
         */
        public Geometry getGeometry() {
            return _geometry;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    /**
     * @param ray ray pointing toward a Geometry
     * @return List of Point3D return values
     */
    List<GeoPoint> findIntersections(Ray ray);
}
