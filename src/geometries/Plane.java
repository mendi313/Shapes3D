package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {
    private Point3D point;
    private Vector normal;

    /***************contractors***********/
    /**
     * contractor for creating a Plane
     *
     * @param p1 get point3D for contractor
     * @param p2 get point3D for contractor
     * @param p3 get point3D for contractor
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        Vector u = p1.subtract(p2);
        Vector v = p1.subtract(p3);
        Vector n = u.crossProduct(v);
        n.normalize();
        this.normal = n.scale(-1);
        this.point = p1;
    }

    public Plane(Point3D p, Vector normal) {
        this.point = new Point3D(p);
        this.normal = new Vector(normal);
    }

/*******************getters****************/


    /**
     * getter
     *
     * @return null
     */
    public Point3D getPoint() {
        return point;
    }

    /**
     * get normal
     *
     * @return normal
     */
    public Vector getNormal() {
        return getNormal(null);
    }

    @Override
    public Vector getNormal(Point3D point) {
        return normal;
    }
}
