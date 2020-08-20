package geometries;

import primitives.Color;
import primitives.Material;

/**
 * Abstract class for any geometry with a single radius
 */
public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    /**
     * constructor for a new extended  RadialGeometry object.
     *
     * @param emissionLight the emission light color
     * @param radius        the radius of the RadialGeometry
     * @param material      the material of the RadialGeometry
     * @throws IllegalArgumentException in any case of radius less or equal to 0
     */
    public RadialGeometry(Color emissionLight, Material material, double radius) {
        super(emissionLight, material);
        _radius = radius;
    }

    /**
     * constructor for a new extended  RadialGeometry object.
     *
     * @param emissionLight  the emission light color
     * @param radius         the radius of the RadialGeometry
     */
    public RadialGeometry(Color emissionLight, double radius) {
        this(emissionLight, new Material(0, 0, 0),radius);
    }

    /**
     * constructor for a new extended  RadialGeometry object.
     *
     * @param radius  the radius of the RadialGeometry
     */
    public RadialGeometry(double radius) {
        this(Color.BLACK,  new Material(0, 0, 0), radius);
    }

    /**
     * Radius getter
     * @return the radius
     */
    public double getRadius() {
        return _radius;
    }
}

