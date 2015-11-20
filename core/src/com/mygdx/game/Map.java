package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {
    TiledMap tiledMap;
    public OrthographicCamera camera;
    public OrthogonalTiledMapRenderer tiledMapRenderer;
    int nTileHeight = 32, nTileWidth = 31;
    TiledMapTileLayer tiledMapTileLayer;
    int nMapWidth, nMapHeight, nTileSize, nSHeight, nSWidth;
    float fTouchPadHeight;
    TiledMapTileLayer collisionLayer;
    int nYTiles;

    void ThumbstickHeight(float _height) {
        fTouchPadHeight = _height;
    }


    public void create() {
        nSHeight = Gdx.graphics.getHeight(); //use to make scaling
        nSWidth = Gdx.graphics.getWidth();
        //  nMapScale = Gdx.graphics.getHeight() * 5 / 1080;
        tiledMap = new TmxMapLoader().load("bombmap2.tmx");
        // System.out.println(fTouchPadHeight);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMapTileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        nTileSize = (int) tiledMapTileLayer.getTileWidth();
        nMapHeight = tiledMapTileLayer.getHeight() * nTileHeight;
        nMapWidth = tiledMapTileLayer.getWidth() * nTileWidth;
        nYTiles = tiledMapTileLayer.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, nSWidth, nSHeight);
       // camera.setToOrtho(false, nSWidth, nSHeight);
        //  camera.setToOrtho(true, nMapWidth, nMapHeight + fTouchPadHeight);
        collisionLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        //LibGdx coordinate systems origin is bottom left, whereas norm is upper left
        //Set cam ortho to true and flip all textureregions so origin is upper left
        camera.update();
    }

   /* public OrthographicCamera getCam() {
        return camera;
    }

    public void setCamera(OrthographicCamera _cam) {
        this.camera = _cam;
    }*/

    public void render() {

        tiledMapRenderer.setView(camera);
        //   tiledMapRenderer.setView(, 0, 0, 500, 500);
        tiledMapRenderer.render();
        camera.update();
    }
}