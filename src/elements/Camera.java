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
     * contractor for camera according to its position
     *
     * @param p0  camera location
     * @param vTo camera TOWARDS direction
     * @param vUp camera UP direction
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
     * function constructs a ray from camera location throw the center of
     * a pixel (i,j) in the view plane
     *
     * @param nX             number of pixel in a row  of view plane
     * @param nY             number of pixel in a column of view plane
     * @param j              number of pixel in a row
     * @param i              number of pixel in a column
     * @param screenDistance distance from camera  to the view plane
     * @param screenWidth    view plane width
     * @param screenHeight   view plane height
     * @return the ray through pixel's center
     */
    public Ray constructRayThroughPixel(int nX, int nY,
                                        int j, int i, double screenDistance,
                                        double screenWidth, double screenHeight) {
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point3D Pc = _p0.add(_vTo.scale(screenDistance));//the view plan center point

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
     * getter for camera location
     *
     * @return the location point
     */
    public Point3D get_p0() {
        return new Point3D(_p0);
    }

    /**
     * getter for the TOWARDS direction vector
     *
     * @return the TOWARDS direction
     */
    public Vector get_vTo() {
        return new Vector(_vTo);
    }

    /**
     * getter for the UP direction vector
     *
     * @return the UP vector
     */
    public Vector get_vUp() {
        return new Vector(_vUp);
    }

    /**
     * getter for the Right direction vector
     *
     * @return the Right vector
     */
    public Vector get_vRight() {
        return new Vector(_vRight);
    }
}
