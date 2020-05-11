package elements;

import primitives.Color;

public class AmbientLight extends Light {

    /***************contractors***********/

    /**
     * contractor for calculate and holding the AmbientLight
     *
     * @param _intensity the color that we wont
     * @param ka         for the clarity
     */
    public AmbientLight(Color _intensity, Double ka) {
        this._intensity = _intensity.scale(ka);
    }
}
