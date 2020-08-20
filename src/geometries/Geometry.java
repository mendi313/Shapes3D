package geometries;

import primitives.*;

/**
 * Interface for all basic geometries providing mandatory methods
 */
public abstract class Geometry implements Intersectable {
    protected Color _emission;
    protected Material _material;

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
     * @param emission the emissionLight of the Geometry
     */
    public Geometry(Color emission) {
        this(emission, new Material(0d, 0d, 0));
    }
    /**
     * constructor for a Geometry emissionLight with default Values for the kd, ks, nShininess and emissionLight.
     */
    public Geometry() {
        this(Color.BLACK, new Material(0d, 0d, 0));
    }

    /**
     * getter for the Emission Light
     *
     * @return Emission
     */
    public Color getEmission() {
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
     * Calculates a unit vector orthogonal to the surface of the geometry body
     * at a given point. Basic assumption - the point lays in the surface
     * @param point in the surface
     * @return unit orthogonal vector
     */
    abstract public Vector getNormal(Point3D point);
}
