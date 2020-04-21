package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;

    /***************contractors***********/
    /**
     * @param p0  get p0 for the contractor
     * @param vTo get vTo for the contractor
     * @param vUp get vUp for the contractor
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {

        //if the the vectors are not orthogonal, throw exception.
        if (vUp.dotProduct(vTo) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");

        this._p0 = new Point3D(p0);
        this._vTo = vTo.normalized();
        this._vUp = vUp.normalized();

        _vRight = this._vTo.crossProduct(this._vUp).normalize();

    }

    /**
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @param screenDistance
     * @param screenWidth
     * @param screenHeight
     * @return
     */
    public Ray constructRayThroughPixel(int nX, int nY,
                                        int j, int i, double screenDistance,
                                        double screenWidth, double screenHeight) {
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point3D Pc = _p0.add(_vTo.scale(screenDistance));

        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;

        if (!isZero(xj)) {
            Pij = Pij.add(_vRight.scale(xj));
        }
        if (!isZero(yi)) {
            Pij = Pij.subtract(_vUp.scale(yi));
        }

        Vector Vij = Pij.subtract(_p0);

        return new Ray(_p0, Vij);

    }

    /**
     * @return
     */
    public Point3D get_p0() {
        return new Point3D(_p0);
    }

    /**
     * @return
     */
    public Vector get_vTo() {
        return new Vector(_vTo);
    }

    /**
     * @return
     */
    public Vector get_vUp() {
        return new Vector(_vUp);
    }

    /**
     * @return
     */
    public Vector get_vRight() {
        return new Vector(_vRight);
    }
}
