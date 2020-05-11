package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {
    Vector _direction;

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
    public SpotLight(Color colorIntensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ) {
        super(colorIntensity, _position, _kC, _kL, _kQ);
        this._direction = new Vector(_direction).normalized();
    }

    /**
     * getter for the Intensity that calculate the affect of the spot on the point
     *
     * @param p The bright spot
     * @return the color in the point
     */
    @Override
    public Color getIntensity(Point3D p) {
        Color pointLightIntensity = super.getIntensity(p);
        double projection = _direction.dotProduct(getL(p));
        Color IL = pointLightIntensity.scale(Math.max(0, projection));
        return IL;
    }

    //instead of getDirection()
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }
}