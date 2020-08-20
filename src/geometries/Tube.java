package geometries;

import primitives.*;
import static primitives.Util.*;
import java.util.List;

/**
 * Tube class represents Euclidean infinite cylinder in 3D Cartesian coordinate
 * system represented by its central ray and radius
 */
public class Tube extends RadialGeometry {
    protected Ray _axis;
    /**
     * Tube constructor given its radius and its central ray
     *
     * @param emissionLight the emission Light of the Tube
     * @param material   the material of the Plane
     * @param radius  is the radius
     * @param r is the ray
     */
    public Tube(Color emissionLight, Material material, double radius, Ray r) {
        super(emissionLight, material, radius);
        _axis = r;
    }
    /**
     * Tube constructor given its radius and its central ray
     *
     *@param emissionLight the emission Light of the Tube
     * @param radius is the radius
     * @param r is the ray
     */
    public Tube(Color emissionLight, double radius, Ray r) {
        this(emissionLight, new Material(0,0,0), radius, r);
    }
    /**
     * constructor for a new Tube object with default Values for the  emissionLight.
     *
     * @param radius the radius of the cylinder
     * @param ray    the direction of the cylinder from a center point
     */
    public Tube(double radius, Ray ray) {
        this(Color.BLACK, new Material(0,0,0), radius, ray);
    }

    @Override
    public Vector getNormal(Point3D point) {
        Point3D o = _axis.getP0();
        Vector v = _axis.getDirection();
        // projection of P-O on the ray:
        double t = point.subtract(o).dotProduct(v);
        if (!isZero(t)) // if it's close to 0, we'll get ZERO vector exception
            o = o.add(v.scale(t));
        return point.subtract(o).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        Vector vAxis = _axis.getDirection();
        Vector v = ray.getDirection();
        Point3D p0 = ray.getP0();

        // At^2+Bt+C=0
        double a = 0;
        double b = 0;
        double c = 0;

        double vVa = alignZero(v.dotProduct(vAxis));
        Vector vVaVa;
        Vector vMinusVVaVa;
        if (vVa == 0) // the ray is orthogonal to the axis
            vMinusVVaVa = v;
        else {
            vVaVa = vAxis.scale(vVa);
            try {
                vMinusVVaVa = v.subtract(vVaVa);
            } catch (IllegalArgumentException e1) { // the rays is parallel to axis
                return null;
            }
        }
        // A = (v-(v*va)*va)^2
        a = vMinusVVaVa.lengthSquared();

        Vector deltaP = null;
        try {
            deltaP = p0.subtract(_axis.getP0());
        } catch (IllegalArgumentException e1) { // the ray begins at axis P0
            if (vVa == 0) // the ray is orthogonal to Axis
                return List.of(new GeoPoint(this,ray.getPoint(_radius)));
            return List.of(new GeoPoint(this,ray.getPoint(Math.sqrt(_radius * _radius / vMinusVVaVa.lengthSquared()))));
        }

        double dPVAxis = alignZero(deltaP.dotProduct(vAxis));
        Vector dPVaVa;
        Vector dPMinusdPVaVa;
        if (dPVAxis == 0)
            dPMinusdPVaVa = deltaP;
        else {
            dPVaVa = vAxis.scale(dPVAxis);
            try {
                dPMinusdPVaVa = deltaP.subtract(dPVaVa);
            } catch (IllegalArgumentException e1) {
                return List.of(new GeoPoint(this,ray.getPoint(Math.sqrt(_radius * _radius / a))));
            }
        }

        // B = 2(v - (v*va)*va) * (dp - (dp*va)*va))
        b = 2 * alignZero(vMinusVVaVa.dotProduct(dPMinusdPVaVa));
        c = dPMinusdPVaVa.lengthSquared() - _radius * _radius;

        // A*t^2 + B*t + C = 0 - lets resolve it
        double discr = alignZero(b * b - 4 * a * c);
        if (discr <= 0) return null; // the ray is outside or tangent to the tube

        double doubleA = 2 * a;
        double tm = alignZero(-b / doubleA);
        double th = Math.sqrt(discr) / doubleA;
        if (isZero(th)) return null; // the ray is tangent to the tube

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 > 0 && t2 > 0)
            return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
        if (t1 > 0) return List.of(new GeoPoint(this,ray.getPoint(t1)));
        if (t2 > 0) return List.of(new GeoPoint(this,ray.getPoint(t2)));

        return null;
    }
}
