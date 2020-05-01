package elements;

import primitives.Color;

public class AmbientLight {
    /**
     * Color obj to hold the color desired
     */
    private Color _intensity;

    /***************contractors***********/

    /**
     * contractor for holding the AmbientLight
     *
     * @param _intensity the color that we wont
     * @param ka         for the clarity
     */
    public AmbientLight(Color _intensity, int ka) {
        // ka is always 1 so we don't use it
        this._intensity = _intensity;
    }

    /**
     * getter for the color
     *
     * @return java.awt.Color
     */
    public java.awt.Color getIntensity() {
        return _intensity.getColor();
    }

    /**
     * setter for the color
     *
     * @param _intensity get the color
     */
    public void setIntensity(Color _intensity) {
        this._intensity = _intensity;
    }
}
