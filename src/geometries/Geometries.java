package geometries;

import primitives.Point3D;
import primitives.Ray;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> _geometries = new ArrayList<Intersectable>();

    /***************contractors***********/

    /**
     * contractors that build List of Geometries
     *
     * @param _geometries get number of different Geometries
     */
    public Geometries(Intersectable... _geometries) {
        add(_geometries);
    }

    /**
     * contractors that build List of Geometries
     *
     * @param intersectables get number of different Geometries
     */
    public Geometries(ArrayList<Intersectable> intersectables) {
        add(intersectables);
    }

    /**
     * func to add Geometries to are List
     *
     * @param intersectables get different type of Geometries
     */
    private void add(ArrayList<Intersectable> intersectables) {
        for (Intersectable geo : intersectables) {
            _geometries.add(geo);
        }
    }

    /**
     * func to add Geometries to are List
     *
     * @param geometries get different type of Geometries
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geo : geometries) {
            _geometries.add(geo);
        }
    }

    /**
     * func to find the Intersections point between the ray and the Geometries
     *
     * @param ray to findIntersections
     * @return list of Point3D that intersect the set of Geometries
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = null;

        for (Intersectable geo : _geometries) {
            List<Point3D> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new ArrayList<Point3D>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }

    /**
     * func to add Geometries to are List
     *
     * @param geometries get different type of Geometries
     */
    public void remove(Intersectable... geometries) {
        for (Intersectable geo : geometries) {
            _geometries.remove(geo);
        }
    }
}