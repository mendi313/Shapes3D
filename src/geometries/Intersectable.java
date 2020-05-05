package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public interface Intersectable {
    public static class GeoPoint {

        Geometry _geometry;
        Point3D point;

        public GeoPoint(Geometry _geometry, Point3D pt) {
            this._geometry = _geometry;
            point = pt;
        }

        public Point3D getPoint() {
            return new Point3D(point);
        }

        public Geometry getGeometry() {
            return _geometry;
        }
    }

    /**
     * @param ray ray pointing toward a Geometry
     * @return List of Point3D return values
     */
    List<GeoPoint> findIntersections(Ray ray);
}
