package primitives;

public class Material {
    private final double _kD; // 0
    private final double _kS; // 0
    private final int _nShininess;  // 1
    private final double _kT; // 0
    private final double _kR; // 0

    /**
     * @param kD         The parameter that represents the diffusion factor of the material.
     * @param kS         The parameter that represents the specularity factor of the material.
     * @param nShininess The parameter that represents the shininess of the material.
     * @param kR         Represents how much the object is reflective.
     * @param kT         Represents the transparency of the object.
     */
    public Material(double kD, double kS, int nShininess, double kR, double kT) {
        if (nShininess < 1) {
            nShininess = 1;
        }
        this._kD = kD;
        this._kS = kS;
        this._nShininess = nShininess;
        this._kR = kR;
        this._kT = kT;
    }

    public Material() {
        this(0d, 0d, 1, 0d, 0d);
    }

    public Material(double kD, double kS, int nShininess) {
        this(kD, kS, nShininess, 0d, 0d);
    }

    public double getKd() {
        return _kD;
    }

    public double getKs() {
        return _kS;
    }

    public int getnShininess() {
        return _nShininess;
    }

    public double getkD() {
        return _kD;
    }

    public double getkS() {
        return _kS;
    }

    public double getKt() {
        return _kT;
    }

    public double getKr() {
        return _kR;
    }
}