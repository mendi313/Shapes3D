package renderer;

import java.util.List;

import scene.Scene;
import primitives.*;
import elements.*;

import static geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

/**
 * Renderer class is responsible for generating pixel color map from a graphic
 * scene, using ImageWriter class
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.0001;

    //Super sampling
    private int _amountRays = 1;

    /**
     * Renderer constructor with image writer and scene
     *
     * @param imgWriter image functionality
     * @param scene     scene details
     */
    public Render(ImageWriter imgWriter, Scene scene) {
        _imageWriter = imgWriter;
        _scene = scene;
    }

    /**
     * Set amount the rays
     * @param amountRays
     * @return this
     */
    public Render setAmountRays(int amountRays) {
       _amountRays = amountRays;
        return this;
    }

    /**
     * Produce a rendered jpeg image file
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */
    public void renderImage() {
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        double dist = _scene.getDistance();
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();
        Camera camera = _scene.getCamera();
        Color bkg = _scene.getBackground();

        for (int i = 0; i < nY; ++i) // go by pixel rows
            for (int j = 0; j < nX; ++j) { // go by pixel in row
                List<Ray> rays = camera.constructNRaysThroughPixel(nX, nY, j, i, dist, width, height, _amountRays);
                Color pixelColor = Color.BLACK;
                for (Ray ray : rays) {
                    GeoPoint closestPoint = findClosestIntersection(ray);
                    pixelColor = pixelColor.add(closestPoint == null ? bkg : calcColor(closestPoint, ray));
                }
                pixelColor = pixelColor.reduce(rays.size()); // for the average color
                _imageWriter.writePixel(j, i, pixelColor.getColor());
            }
    }

    /**
     * Find intersections of a ray with the scene geometries and get the
     * intersection point that is closest to the ray head. If there are no
     * intersections, null will be returned.
     *
     * @param ray intersecting the scene
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        if (ray == null) return null;
        List<GeoPoint> intersections = _scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null) return null;
        Point3D p0 = ray.getP0();

        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        for (GeoPoint geoPoint : intersections) {
            double distance = p0.distance(geoPoint._point);
            if (distance < closestDistance) {
                closestPoint = geoPoint;
                closestDistance = distance;
            }
        }
        return closestPoint;
    }

    /**
     * calculate the color of a point
     *
     * @param inRay the ray intersect the point
     * @param k     factor of every iteration
     * @param level level of recursion
     * @param p     requested point
     * @return calculated color of the point
     */
    private Color calcColor(GeoPoint p, Ray inRay, int level, double k) {
        List<LightSource> lights = _scene.getLightSources();
        Color color = p._geometry.getEmission();

        Vector v = inRay.getDirection();
        Vector n = p._geometry.getNormal(p._point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;

        // Local effects (lighting)
        Material material = p._geometry.getMaterial();
        int shine = material.getNShininess();
        double kd = material.getKd();
        double ks = material.getKs();
        if (lights != null) {
            for (LightSource light : lights) {
                Vector l = light.getL(p._point);
                double nl = alignZero(n.dotProduct(l));
                if (nl * nv > 0) {
                    double ktr = transparency(light, l, n, p);
                    if (ktr * k > MIN_CALC_COLOR_K) {
                        Color ip = light.getIntensity(p._point).scale(ktr);
                        color = color.add(
                                calcDiffusive(kd, nl, ip),
                                calcSpecular(ks, l, n, nl, v, shine, ip));
                    }
                }
            }
        }

        // Global [recursive] effects
        if (level == 1) return color; // no need for recursion - stop condition will be reached
        double kr = material.getKr(), kkr = k * kr;
        double kt = material.getKt(), kkt = k * kt;
        if (kkr > MIN_CALC_COLOR_K) { // stop condition check before doing recursion
            Ray reflectedRay = constructReflectedRay(p._point, inRay, n, nv);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null)
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }
        if (kkt > MIN_CALC_COLOR_K) { // stop condition check before doing recursion
            Ray refractedRay = constructRefractedRay(p._point, inRay, n);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null)
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }

    /**
     * Calculate the color intensity in a point
     * @param geoPoint the point for which the color is required.
     * @param inRay    the ray
     * @return the color intensity
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay) {
        Color color = calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0);
        color = color.add(_scene.getAmbientLight().getIntensity());
        return color;
    }

    /**
     * @param inRay List of surrounding rays
     * @return average color
     * @author Reuven Smadja
     */
    private Color calcColor(List<Ray> inRay) {
        Color bkg = _scene.getBackground();
        Color color = Color.BLACK;
        for (Ray ray : inRay) {
            GeoPoint gp = findClosestIntersection(ray);
            color = color.add(gp == null ? bkg : calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1d));
        }
        color = color.add(_scene.getAmbientLight().getIntensity());
        int size = inRay.size();
        return (size == 1) ? color : color.reduce(size);
    }

    /**
     * Create a grid [over the picture] in the pixel color map. given the grid's
     * step and color.
     * @param step  grid's step
     * @param color grid's color
     */
    public void printGrid(int step, java.awt.Color color) {
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();

        for (int i = 0; i < nY; ++i)
            for (int j = 0; j < nX; ++j)
                if (j % step == 0 || i % step == 0)
                    _imageWriter.writePixel(j, i, color);
    }

    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular component coef
     * @param l          direction from light to point
     * @param n          normal to surface at the point
     * @param nl         dot-product n*l
     * @param v          direction from point of view to point
     * @param nShininess shininess level
     * @param ip         light intensity at the point
     * @return specular component light effect at the point
     */
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        Vector r = l.add(n.scale(-2 * nl));
        double minusVR = alignZero(v.dotProduct(r));
        if (minusVR >= 0) return Color.BLACK; // view from direction opposite to r vector
        return ip.scale(ks * Math.pow(minusVR, nShininess));
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component coef
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     */
    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) nl = -nl;
        return ip.scale(nl * kd);
    }

    /**
     * calculate the refracted effect
     *
     * @param n        normal
     * @param pointGeo point on the shape
     * @param inRay    the intersected ray
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.getDirection(), n);
    }

    /**
     * Calculate the reflection effect
     *
     * @param n        normal
     * @param pointGeo on the shape
     * @param inRay    the intersected ray
     * @param nv       n*v (dot-product) is already calculated
     * @return the reflection ray
     */
    private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n, double nv) {
        Vector v = inRay.getDirection();
        Vector r = v.subtract(n.scale(2 * nv));
        return new Ray(pointGeo, r, n);
    }

    /**
     * handling reflection and refraction
     *
     * @param light    the light source
     * @param l        vector l
     * @param n        vector n
     * @param geoPoint the shape with the intersected point
     * @return a number for stop condition
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint._point, lightDirection, n);
        Point3D pointGeo = geoPoint._point;

        List<GeoPoint> intersections = _scene.getGeometries().findGeoIntersections(lightRay);
        if (intersections == null) return 1d;

        double lightDistance = light.getDistance(pointGeo);
        double ktr = 1d;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp._point.distance(pointGeo) - lightDistance) <= 0) {
                ktr *= gp._geometry.getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }
}
