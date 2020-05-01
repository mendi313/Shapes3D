package geometries;

import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.isZero;

public abstract class RadialGeometry implements Geometry {
    double _radius;

    /***************contractors***********/

    /**
     * contractor for creating a RadialGeometry
     *
     * @param _radius get radios for contractor
     */
    public RadialGeometry(double _radius) {
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
