package geometries;

import primitives.Point3D;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry {
    double _radius;

    /**
     * contractor for creating a RadialGeometry
     *
     * @param _radius get radios for contractor
     */
    public RadialGeometry(double _radius) {
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
     * getter
     *
     * @return radios
     */
    public double get_radius() {
        return _radius;
    }

}
