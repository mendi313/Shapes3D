package primitives;

import java.util.Objects;

public class Ray {
    private Point3D _p0;
    private Vector _dir;

/***************contractors***********/
    /**
     * contractor for creating a Ray
     *
     * @param _p0  get point3D for contractor
     * @param _dir get vector for contractor
     */
    public Ray(Point3D _p0, Vector _dir) {
        this._dir = new Vector(_dir.normalize());
        this._p0 = new Point3D(_p0);
    }

    /**
     * getter
     *
     * @return _p0
     */
    public Point3D get_p0() {
        return new Point3D(_p0);
    }

    /**
     * getter
     *
     * @return _dir
     */
    public Vector get_dir() {
        return new Vector(_dir);
    }

    /************toString() and equals() ****/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) &&
                _dir.equals(ray._dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "_p0=" + _p0 +
                ", _dir=" + _dir +
                '}';
    }
}
