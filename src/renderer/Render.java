package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import primitives.*;
import geometries.Intersectable.GeoPoint;

import scene.Scene;
import java.util.List;

public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;

    /***************contractors***********/

    /**
     * contractors for build a scene with image
     *
     * @param imageWriter param to build a image
     * @param scene       param to build a scene
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    /**
     * contractors that get the scene
     *
     * @param _scene for getting the scene
     */
    public Render(Scene _scene) {
        this._scene = _scene;
    }

    /**
     * func that writhing image
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }


    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage() {
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double distance = _scene.getDistance();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        int width = (int) _imageWriter.getWidth();
        int height = (int) _imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx(); //columns
        int Ny = _imageWriter.getNy(); //rows
        //pixels grid
        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    java.awt.Color pixelColor = calcColor(closestPoint).getColor();
                    _imageWriter.writePixel(column, row, pixelColor);
                }
            }
        }
    }

    /**
     * getter for the scene
     *
     * @return the scene
     */
    public Scene get_scene() {
        return _scene;
    }

    /**
     * Finding the closest point to the P0 of the camera.
     *
     * @param intersectionPoints list of points, the function should find from
     *                           this list the closet point to P0 of the camera in the scene.
     * @return the closest point to the camera
     */
    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        GeoPoint result = null;
        double mindist = Double.MAX_VALUE;

        Point3D p0 = this._scene.getCamera().get_p0();

        for (GeoPoint geo : intersectionPoints) {
            Point3D pt = geo.getPoint();
            double distance = p0.distance(pt);
            if (distance < mindist) {
                mindist = distance;
                result = geo;
            }
        }
        return result;
    }

    /**
     * Printing the grid with a fixed interval between lines
     *
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval, java.awt.Color colorsep) {
        double rows = this._imageWriter.getNx();
        double columns = _imageWriter.getNy();
        //Writing the lines.
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                if (col % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(row, col, colorsep);
                }
            }
        }
    }

    /**
     * Calculate the color intensity in a point
     *
     * @param intersection the point for which the color is required
     * @return the color intensity
     */
    private Color calcColor(GeoPoint intersection) {
        Color resultColor;
        Color ambientLight = _scene.getAmbientLight().getIntensity();
        Color emissionLight = intersection.getGeometry().getEmissionLight();
        List<LightSource> lights = _scene.getLightSources();

        resultColor = ambientLight;
        resultColor = resultColor.add(emissionLight);

        Vector v = intersection.getPoint().subtract(_scene.getCamera().get_p0()).normalize();
        Vector n = intersection.getGeometry().getNormal(intersection.getPoint());
        Material material = intersection.getGeometry().getMaterial();

        int nShininess = material.getnShininess();
        double kd = material.getkD();
        double ks = material.getkS();

        if (lights != null) {
            for (LightSource lightSource : lights) {
                Vector l = lightSource.getL(intersection.getPoint());
                if (sign(n.dotProduct(l)) == sign(n.dotProduct(v))) {
                    Color lightIntensity = lightSource.getIntensity(intersection.getPoint());
                    resultColor = resultColor.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }

        return resultColor;
    }

    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        return Color.BLACK;
    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        return Color.BLACK;
    }

    private boolean sign(double val) {
        return (val > 0d);
    }

}