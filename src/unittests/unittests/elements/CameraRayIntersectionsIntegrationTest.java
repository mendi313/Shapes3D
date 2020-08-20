package unittests.elements;

import elements.Camera;
import geometries.Intersectable.GeoPoint;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing Camera Integration Class
 */
public class CameraRayIntersectionsIntegrationTest {

    /**
     * Test helper function to count the intersections and compare with expected value
     *
     * @param cam      camera for the test
     * @param geo      3D body to test the integration of the camer with
     * @param expected amount of intersections
     */
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected) {
        List<GeoPoint> intersections;
        int count = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                intersections = geo.findGeoIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (intersections != null)
                    count += intersections.size();
            }
        assertEquals("Wrong amount of intersections", expected, count);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    public void CameraRaySphereIntegration() {
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));

        // TC01: Small Sphere 2 points
        assertCountIntersections(cam1, new Sphere(1, new Point3D(0, 0, 3)), 2);

        // TC02: Big Sphere 18 points
        assertCountIntersections(cam2, new Sphere(2.5, new Point3D(0, 0, 2.5)), 18);

        // TC03: Medium Sphere 10 points
        assertCountIntersections(cam2, new Sphere(2, new Point3D(0, 0, 2)), 10);

        // TC04: Inside Sphere 9 points
        assertCountIntersections(cam2, new Sphere(4, new Point3D(0, 0, 1)), 9);

        // TC05: Beyond Sphere 0 points
        assertCountIntersections(cam1, new Sphere(0.5, new Point3D(0, 0, -1)), 0);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void CameraRayPlaneIntegration() {
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));

        // TC01: Plane against camera 9 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, 5), new Vector(0, 0, 1)), 9);

        // TC02: Plane with small angle 9 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, 5), new Vector(0, -1, 2)), 9);

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, 5), new Vector(0, -1, 1)), 6);

        // TC04: Beyond Plane 0 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, 5), new Vector(0, -1, 1)), 6);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void CameraRayTriangleIntegration() {
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));

        // TC01: Small triangle 1 point
        assertCountIntersections(cam, new Triangle(new Point3D(1, 1, 2), new Point3D(-1, 1, 2), new Point3D(0, -1, 2)), 1);

        // TC02: Medium triangle 2 points
        assertCountIntersections(cam, new Triangle(new Point3D(1, 1, 2), new Point3D(-1, 1, 2), new Point3D(0, -20, 2)), 2);
    }

}




