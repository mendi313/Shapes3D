package elements;

import primitives.Color;

public abstract class Light {
    protected Color _intensity;

    public Color getIntensity() {
        return new Color(_intensity);
    }
}