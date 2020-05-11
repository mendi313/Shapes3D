package elements;

import primitives.Color;

public abstract class Light {
    protected Color _intensity;

    /***************contractors***********/

    /**
     * getter for the Intensity
     *
     * @return Intensity color
     */
    public Color getIntensity() {
        return new Color(_intensity);
    }
}