package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

public class SpotLight extends PointLight {
    Vector _direction;
    double _concentration;

    /***************contractors***********/

    /**
     * Initialize Spot light with it's intensity and position
     *
     * @param colorIntensity get the color Intensity
     * @param _position      get the position of the spot
     * @param _direction     get the direction of the spot
     * @param _kC            factors for attenuation with distance
     * @param _kL            factors for attenuation with distance
     * @param _kQ            factors for attenuation with distance
     */
    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ, double concentration) {
        super(colorIntensity, position, kC, kL, kQ);
        this._direction = new Vector(direction).normalized();
        this._concentration = concentration;
    }

    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        this(colorIntensity, position, direction, kC, kL, kQ, 1);
    }


        /**
         * getter for the Intensity that calculate the affect of the spot on the point
         *
         * @param p The bright spot
         * @return the color in the point
         */
    @Override
    public Color getIntensity(Point3D p) {
        double projection = _direction.dotProduct(getL(p));

        if (Util.isZero(projection)) {
            return Color.BLACK;
        }
        double factor = Math.max(0, projection);
        Color pointlightIntensity = super.getIntensity(p);

        if (_concentration != 1) {
            factor = Math.pow(factor, _concentration);
        }

        return (pointlightIntensity.scale(factor));
    }
}