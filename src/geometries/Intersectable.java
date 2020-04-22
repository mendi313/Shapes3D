package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public interface Intersectable {
    /**
     *
     * @param ray ray pointing toward a Geometry
     * @return List of Point3D return values
     */
    List<Point3D> findIntersections(Ray ray);
}
