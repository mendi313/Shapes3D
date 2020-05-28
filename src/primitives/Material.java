package primitives;

public class Material {
    private final double _kD;
    private final double _kS;
    private final int _nShininess;
    private final double _kr;
    private final double _kt;

    /***************contractors***********/

    /**
     * @param kD
     * @param kS
     * @param nShininess
     * @param kt
     * @param kr
     */
    public Material(double kD, double kS, int nShininess, double kt, double kr) {
        this._kD = kD;
        this._kS = kS;
        this._nShininess = nShininess;
        this._kt = kt;
        this._kr = kr;
    }

    /**
     * @param kD
     * @param kS
     * @param nShininess
     */
    public Material(double kD, double kS, int nShininess) {
        this(kD, kS, nShininess, 0, 0);
    }

    /**
     * @param material
     */
    public Material(Material material) {
        this(material._kD, material._kS, material._nShininess, material._kt, material._kr);
    }

    /**
     * getter for the nShininess(
     *
     * @return int Shininess
     */
    public int getnShininess() {
        return _nShininess;
    }

    /**
     * getter for the kD
     *
     * @return double kD
     */
    public double getkD() {
        return _kD;
    }

    /**
     * getter for the kS
     *
     * @return double kS
     */
    public double getkS() {
        return _kS;
    }

    /**
     * getter for the kr
     *
     * @return double Kr
     */
    public double getKr() {
        return _kr;
    }

    /**
     * getter for the kT
     *
     * @return double Kt
     */
    public double getKt() {
        return _kt;
    }
}