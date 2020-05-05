package unittests.elements;

import elements.Camera;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

public class CameraRayIntersectionsIntegrationTest {
    Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
    Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));

    @Test
    public void CameraRaySphereIntegration() {

        Sphere sphere;
        List<Intersectable.GeoPoint> intersections;
        int count;

        // TC01 small Sphere 2 points
        sphere = new Sphere(1, new Point3D(0, 0, 3));
        count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = sphere.findIntersections(cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("wrong amount of intersections", 2, count);

        // TC02 Big Sphere 18 points
        sphere = new Sphere(2.5, new Point3D(0, 0, 2.5));
        count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = sphere.findIntersections(cam2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("wrong amount of intersections", 18, count);

        // TC03 Medium Sphere 10 points
        sphere = new Sphere(2, new Point3D(0, 0, 2));
        count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = sphere.findIntersections(cam2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("wrong amount of intersections", 10, count);

        // TC04 inside the Sphere 9 points
        sphere = new Sphere(4, new Point3D(0, 0, 0));
        count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = sphere.findIntersections(cam2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("wrong amount of intersections", 9, count);

        // TC05  Sphere behind the camera 0 points
        sphere = new Sphere(0.5, new Point3D(0, 0, -1));
        count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = sphere.findIntersections(cam2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("wrong amount of intersections", 0, count);
    }


    @Test
    public void CameraRayPlaneIntegration() {
        Plane plane;
        // TC01 stand Plane 9 points
        plane = new Plane(new Point3D(0, 0, 2), new Vector(0, 0, -1));
        List<Intersectable.GeoPoint> intersections;
        int count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = plane.findIntersections(cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("wrong amount of intersections", 9, count);

        // TC02 Plane at an angle 9 points
        plane = new Plane(new Point3D(0, 0, 2), new Vector(0, 0, -4));
        count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = plane.findIntersections(cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("wrong amount of intersections", 9, count);

        // TC03 Plane at an angle 6 points
        plane = new Plane(new Point3D(0, 0, 2), new Vector(-5, -2, 3));
        count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = plane.findIntersections(cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("wrong amount of intersections", 6, count);
    }

    @Test
    public void CameraRayTriangleIntegration() {
        Triangle triangle;
        // TC01 small triangle 1 points
        triangle = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        List<Intersectable.GeoPoint> intersections;
        int count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = triangle.findIntersections(cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("wrong amount of intersections", 1, count);

        // TC02 Big triangle 2 points
        triangle = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = triangle.findIntersections(cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("wrong amount of intersections", 2, count);
    }

}
