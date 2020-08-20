package elements;

import primitives.*;

import static primitives.Util.*;

import java.util.LinkedList;
import java.util.List;


/**
 * Class representing Camera (from the point of view to a picture)
 */
public class Camera {
    private Point3D _p0;
    private Vector _vUp, _vTo, _vRight;

    /**
     * Constructor for camera according to its position and orientation NB: RIGHT
     * direction is calculated automatically
     *
     * @param p0  camera location
     * @param vTo camera TOWARDS direction
     * @param vUp camera UP direction
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("Camera UP and TOWARDS dirtection aren't orthogonal");
        _p0 = new Point3D(p0);
        _vTo = vTo.normalized();
        _vUp = vUp.normalized();
        _vRight = _vTo.crossProduct(_vUp);
    }

    /**
     * The function constructs a ray from Camera location throw the center of a
     * pixel (i,j) in the view plane
     *
     * @param nX             number of pixels in a row of view plane
     * @param nY             number of pixels in a column of view plane
     * @param j              number of the pixel in a row
     * @param i              number of the pixel in a column
     * @param screenDistance distance from the camera to the view plane
     * @param screenWidth    view plane width
     * @param screenHeight   view plane height
     * @return the ray through pixel's center
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i,
                                        double screenDistance, double screenWidth, double screenHeight) {
        double rX = screenWidth / nX;
        double rY = screenHeight / nY;
        double xJ = (j - (nX - 1) / 2d) * rX;
        double yI = (i - (nY - 1) / 2d) * rY;

        Point3D pIJ = _p0.add(_vTo.scale(screenDistance)); // the view plane center point
        if (xJ != 0) pIJ = pIJ.add(_vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(_vUp.scale(-yI)); // it's possible pIJ.subtract(_vUp.scale(yI));

        return new Ray(_p0, pIJ.subtract(_p0));
    }

    /**
     * The function constructs a rays from the camera position to throw the pixel (i, j) in the view plane
     *
     * @param nX         number of pixels in a row of view plane
     * @param nY         number of pixels in a column of view plane
     * @param j          number of the pixel in a row
     * @param i          number of the pixel in a column
     * @param distance   distance from the camera to the view plane
     * @param width      view plane width
     * @param height     view plane height
     * @param amountRays amount rays for Pixel
     * @return list of rays through pixel
     */
    public List<Ray> constructNRaysThroughPixel(int nX, int nY, int j, int i,
                                                double distance, double width, double height, int amountRays) {
        double rX = width / nX;
        double rY = height / nY;
        Point3D pC = _p0.add(_vTo.scale(distance));// Pc is The middle pixel

        double xj = ((j - nX / 2d) * rX + rX / 2d);
        double yi = ((i - nY / 2d) * rY + rY / 2d);

        Point3D pIJ = pC;

        if (!isZero(yi))
            pIJ = pIJ.add(_vUp.scale(-yi));
        if (!isZero(xj))
            pIJ = pIJ.add(_vRight.scale(xj));

        Ray centralRay = new Ray(_p0, pIJ.subtract(_p0));
        if (amountRays <= 1) return List.of(centralRay);

        List<Ray> rays = new LinkedList<>();

        int delta = amountRays - 1; //shoresh

        double gapX = rX / delta;
        double gapY = rY / delta;

        double xStart = ((j - nX / 2d) * rX);
        double y = ((i - nY / 2d) * rY);
        for (int row = 0; row < amountRays; ++row, y += gapY) {//r=rows from up to down.
            double x = xStart;
            for (int col = 0; col < amountRays; ++col, x += gapX) {//col=column from left to right.
                Point3D tmp = pC;
                if (!isZero(y))
                    tmp = tmp.add(_vUp.scale(-y));
                if (!isZero(x))
                    tmp = tmp.add(_vRight.scale(x));
                rays.add(new Ray(_p0, tmp.subtract(_p0).normalize()));//create the ray from camera to the temp point on pixel.
            }
        }
        return rays;
    }

    /**
     * Getter for camera location
     *
     * @return the location point
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * Getter for the UP direction vector
     *
     * @return the up vector
     */
    public Vector getVUp() {
        return _vUp;
    }

    /**
     * Getter for the TOWARDS direction vector
     *
     * @return the the to vector
     */
    public Vector getVTo() {
        return _vTo;
    }

    /**
     * Getter for the RIGHT direction vector
     *
     * @return the right vector
     */
    public Vector getVRight() {
        return _vRight;
    }
}
