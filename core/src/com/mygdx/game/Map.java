package com.mygdx.game;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {
    TiledMap tiledMap;
    public OrthographicCamera camera;
    public OrthogonalTiledMapRenderer tiledMapRenderer;
    int nTileHeight = 32, nTileWidth=31;
    TiledMapTileLayer tiledMapTileLayer;
    int nMapWidth, nMapHeight, nTileSize;
    float fTouchPadHeight;
    TiledMapTileLayer collisionLayer;

    void ThumbstickHeight(float _height) {
        fTouchPadHeight = _height;
    }


    public void create() {
        //  nMapScale = Gdx.graphics.getHeight() * 5 / 1080;
        tiledMap = new TmxMapLoader().load("testmap.tmx");
        System.out.println(fTouchPadHeight);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMapTileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        nTileSize = (int) tiledMapTileLayer.getTileWidth();
        nMapHeight = tiledMapTileLayer.getHeight() * nTileHeight;
        nMapWidth = tiledMapTileLayer.getWidth() * nTileWidth;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, nMapWidth, nMapHeight);
      //  camera.setToOrtho(true, nMapWidth, nMapHeight + fTouchPadHeight);
        collisionLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        //LibGdx coordinate systems origin is bottom left, whereas norm is upper left
        //Set cam ortho to true and flip all textureregions so origin is upper left
        camera.update();
    }


    public void render() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        camera.update();
    }
}