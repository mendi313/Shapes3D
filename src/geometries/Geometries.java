package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;

/**
 * Composite class which include a collection of any base and composite geometries
 */
public class Geometries implements Intersectable {
    private List<Intersectable> _geometries = new LinkedList<Intersectable>();

    /**
     * Geometries constructor allowing to add zero or more geometries
     * while creating it
     * @param geometries to add to the collection
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * The function add allows to add zero or more geometries to the
     * composite geometry
     * @param geometries to add to the collection
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries)
            _geometries.add(geometry);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : _geometries) {
            List<GeoPoint> tempIntersections = geometry.findGeoIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}
