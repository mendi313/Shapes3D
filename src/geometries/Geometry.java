package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * interface for all geometries with getNormal
 */
public interface Geometry extends Intersectable {

    /**
     * func that calculate the Normal vector
     *
     * @param point to calculate the normal vector from her
     * @return normal vector
     */
    public Vector getNormal(Point3D point);
}
