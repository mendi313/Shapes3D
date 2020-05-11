package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface for all geometries with getNormal
 */
public abstract class Geometry implements Intersectable {
    protected Color _emission;
    protected Material _material;

    /***************contractors***********/

    /**
     * constructor for a Geometry emissionLight and attenuation
     *
     * @param _emission the emissionLight of the Geometry
     * @param _material the attenuation
     */
    public Geometry(Color _emission, Material _material) {
        this._emission = _emission;
        this._material = _material;
    }

    /**
     * constructor for a Geometry emissionLight with default Values for the kd, ks and nShininess.
     *
     * @param _emission the emissionLight of the Geometry
     */
    public Geometry(Color _emission) {
        this(_emission, new Material(0d, 0d, 0));
    }

    /**
     * getter for the Emission Light
     *
     * @return Emission Light
     */
    public Color getEmissionLight() {
        return (_emission);
    }

    /**
     * getter for the Material
     *
     * @return Material type
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * func that calculate the Normal vector
     *
     * @param p to calculate the normal vector from her
     * @return normal vector
     */
    abstract public Vector getNormal(Point3D p);
}
