package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    Point3D _position;
    double _kC;
    double _kL;
    double _kQ;

    /***************contractors***********/

    /**
     * Initialize Point light with it's intensity and position
     *
     * @param colorIntensity get the color Intensity
     * @param _position      get the position of the spot
     * @param _kC            factors for attenuation with distance
     * @param _kL            factors for attenuation with distance
     * @param _kQ            factors for attenuation with distance
     */
    public PointLight(Color colorIntensity, Point3D _position, double _kC, double _kL, double _kQ) {
        this._intensity = colorIntensity;
        this._position = new Point3D(_position);
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;

    }

    /**
     * getter for the Intensity that calculate the affect of the pointLight on the point
     *
     * @param p The bright spot
     * @return intensity of the pointLight
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);

        Color IL = _intensity.reduce(_kC + d * _kL + dsquared * _kQ);

        return IL;
    }

    //instead of getDirection()
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        } else {
            return p.subtract(_position).normalize();
        }
    }
}