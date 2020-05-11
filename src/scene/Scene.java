package scene;

import elements.*;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private String _name;
    private Color _background;
    private AmbientLight _ambientLight;
    private Geometries _geometries;
    private Camera _camera;
    private double _distance;
    private List<LightSource> _lights = null;

    /***************contractors***********/
    /**
     * contractors for named the scene and build empty Geometries
     *
     * @param _sceneName to named the scene
     */
    public Scene(String _sceneName) {
        this._name = _sceneName;
        _geometries = new Geometries();
    }

    /**
     * setter for Background Color
     *
     * @param _background get the color
     */
    public void setBackground(Color _background) {
        this._background = _background;
    }

    /**
     * setter for AmbientLight
     *
     * @param _ambientLight get the AmbientLight
     */
    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    /**
     * setter for Geometries
     *
     * @param _geometries get the Geometries
     */
    public void setGeometries(Geometries _geometries) {
        this._geometries = _geometries;
    }

    /**
     * setter for Camera
     *
     * @param _camera get the camera
     */
    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    /**
     * setter for Distance
     *
     * @param _distance get the Distance
     */
    public void setDistance(double _distance) {
        this._distance = _distance;
    }

    /**
     * getter for AmbientLight
     *
     * @return AmbientLight
     */
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    /**
     * getter for Camera
     *
     * @return Camera
     */
    public Camera getCamera() {
        return _camera;
    }

    /**
     * getter for Geometries
     *
     * @return Geometries
     */
    public Geometries getGeometries() {
        return _geometries;
    }

    /**
     * getter for Distance
     *
     * @return double Distance
     */
    public double getDistance() {
        return _distance;
    }

    /**
     * getter for Background Color
     *
     * @return Background Color
     */
    public Color getBackground() {
        return this._background;
    }

    /**
     * add func to add Geometries to the Scene
     *
     * @param intersectables
     */
    public void addGeometries(Intersectable... intersectables) {
        for (Intersectable i : intersectables) {
            _geometries.add(i);
        }
    }

    /**
     * add func to add LightSource to the Scene
     *
     * @param light get the LightSources
     */
    public void addLights(LightSource light) {
        if (_lights == null) {
            _lights = new ArrayList<>();
        }
        _lights.add(light);
    }

    /**
     * getter for the LightSources
     *
     * @return LightSource
     */
    public List<LightSource> getLightSources() {
        return _lights;
    }
}
