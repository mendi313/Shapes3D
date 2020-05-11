package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Cylinder is a finite Tube with a certain _height
 */
public class Cylinder extends Tube {
    private double _height;

    /**
     * Cylinder constructor with default Values for the kd, ks, emissionLight, and nShininess.
     *
     * @param _radius the radius of the Cylinder
     * @param _ray    the ray that we build around her the Cylinder
     * @param _height the height og the Cylinder
     */
    public Cylinder(double _radius, Ray _ray, double _height) {
        super(Color.BLACK, new Material(0, 0, 0), _radius, _ray);
        this._height = _height;


    }

    /**
     * Cylinder constructor with default Values for the kd, ks and nShininess.
     *
     * @param emissionLight the emissionLight of the Cylinder
     * @param _radius       the radius of the Cylinder
     * @param _ray          the ray that we build around her the Cylinder
     * @param _height       the height og the Cylinder
     */
    public Cylinder(Color emissionLight, double _radius, Ray _ray, double _height) {
        super(emissionLight, new Material(0, 0, 0), _radius, _ray);
        this._height = _height;
    }

    /**
     * @param emissionLight the emissionLight of the Cylinder
     * @param _material     the attenuation
     * @param _radius       the radius of the Cylinder
     * @param _ray          the ray that we build around her the Cylinder
     * @param _height       the height og the Cylinder
     */
    public Cylinder(Color emissionLight, Material _material, double _radius, Ray _ray, double _height) {
        super(emissionLight, _radius, _ray);
        this._material = _material;
        this._height = _height;
    }

    /**
     * func to calculate the normal
     *
     * @param point point to calculate the normal
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D o = _ray.getPoint();
        Vector v = _ray.getDirection();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(point.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return point.subtract(o).normalize();
    }

    /**
     * get the height
     *
     * @return height
     */
    public double get_height() {
        return _height;
    }

    /**
     * func to find the Intersections point between the ray and the Cylinder
     *
     * @param ray ray pointing toward a Geometry
     * @return List<Point3D> with the Intersections point
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = super.findIntersections(ray);
        if (intersections != null) {
            for (GeoPoint geoPoint : intersections) {
                geoPoint._geometry = this;
            }
        }
        return intersections;
    }
}