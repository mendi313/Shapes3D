package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.isZero;

public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    /***************contractors***********/

    /**
     * contractor for creating a RadialGeometry
     *
     * @param emissionLight get the emissionLight of the Radial
     * @param _radius       the radius of the Radial
     */
    public RadialGeometry(Color emissionLight, double _radius) {
        super(emissionLight);
        if (isZero(_radius) || (_radius < 0.0))
            throw new IllegalArgumentException("radius " + _radius + " is not valid");
        this._radius = _radius;
    }

    /**
     * copy contractor
     *
     * @param other get radios for copy contractor
     */
    public RadialGeometry(RadialGeometry other) {
        super(Color.BLACK);
        this._radius = other._radius;
    }

    /**
     * getter for the radios
     *
     * @return radios point
     */
    public double get_radius() {
        return _radius;
    }

}
