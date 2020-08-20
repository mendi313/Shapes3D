package primitives;

/**
 * The class represents a material of object
 */
public class Material {
    private double _kD;
    private double _kS;
    private int _nShininess;
    private final double _kr;
    private final double _kt;

    /***************constructors***********/

    /**
     * constructor for a new material object
     *
     * @param kD          how much it reflects
     * @param kS          how much it shiny
     * @param nShininess  how small is specular
     * @param kt          Promotes transparency
     * @param kr          Promotes reflection
     */
    public Material(double kD, double kS, int nShininess, double kt, double kr) {
        _kD = kD;
        _kS = kS;
        _nShininess = nShininess;
        _kt = kt;
        _kr = kr;
    }

    /**
     * constructor for a new material object
     *
     * @param kD          how much it reflects
     * @param kS          how much it shiny
     * @param nShininess  how small is specular
     */
    public Material(double kD, double kS, int nShininess) {
        this(kD, kS, nShininess, 0, 0);
    }

    /***************getter***********/
    /**
     * getter for the kD
     *
     *   @return kD
     */
    public double getKd() {
        return _kD;
    }
    /**
     * getter for the kS
     *
     *   @return kS
     */
    public double getKs() {
        return _kS;
    }

    /**
     * getter for the nShininess
     *
     *   @return nShininess
     */
    public int getNShininess() {
        return _nShininess;
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