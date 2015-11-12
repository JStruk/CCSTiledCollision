package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


/**
 * Created by Justin on 2015-11-02.
 */
public class Character implements ApplicationListener {
    SpriteBatch batch;
    TextureAtlas taBomberman;
    Sprite[] spBomberman;
    int i = 0, nCurrentIndex;
    float stateTime, fCharacterVelocityX = 0, fCharacterVelocityY = 0, fCharacterX, fCharacterY, fCharacterWidth, fOldX, fOldY;
    int nVelocityX, nVelocityY;
    boolean[] arbDirection = new boolean[4];//0=up, 1=down, 2=right, 3=left
    boolean bStop = true, bCollidedX, bCollidedY;
    Sprite sprChar;
    int nSHeight, nSWidth, nLayerCount, nC = 0, nTileHeight, nTileWidth;
    Map map;

    public void setMap(Map _map) {
        map = _map;
    }

    public void create() {
        nSHeight = Gdx.graphics.getHeight(); //use to make scaling
        nSWidth = Gdx.graphics.getWidth();
        nVelocityX = nSWidth * 10 / 1794;
        nVelocityY = nSHeight * 10 / 1080;
        fCharacterWidth = nSWidth * 110 / 1794;
        arbDirection[0] = true;
        batch = new SpriteBatch();
        taBomberman = new TextureAtlas(Gdx.files.internal("bomberchar.pack"));
        spBomberman = new Sprite[4];
        sprChar = new Sprite();
        fCharacterX = (map.nMapWidth - 200);
        fCharacterY = map.nMapHeight - 350;
        nTileHeight = (int) map.collisionLayer.getTileHeight();
        nTileWidth = (int) map.collisionLayer.getTileWidth();
    }

    @Override
    public void resize(int width, int height) {

    }


    public void setCharacterVelocity(int _nVx, int _nVy) {
        fCharacterVelocityX = nVelocityX * _nVx;
        fCharacterVelocityY = nVelocityY * _nVy;
    }

    public void render() {
        fOldX = fCharacterX;
        fOldY = fCharacterY;
        bCollidedX = false;
        bCollidedY = false;
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        fCharacterX += fCharacterVelocityX / 2;
        fCharacterY += fCharacterVelocityY / 2;
        sprChar = new Sprite(taBomberman.findRegion("frame-0"));// (int) ((fCharacterY+sprChar.getHeight()/2)/nTileHeight)
        if (map.collisionLayer.getCell((int) (fCharacterX / nTileWidth), ((int) (16-((fCharacterY + sprChar.getHeight() / 2) / nTileHeight)))) != null) {//leftcollision
            bCollidedX = map.collisionLayer.getCell((int) (fCharacterX / nTileWidth), ((int) (16-((fCharacterY + sprChar.getHeight() / 2) / nTileHeight)))).getTile().getProperties().containsKey("Block");
            System.out.println("X: "+(int) (fCharacterX / nTileWidth)+" Y: "+(int) (16-((fCharacterY + sprChar.getHeight() / 2) / nTileHeight)));
        }
           // if (!bCollidedX) {
       /* if(map.collisionLayer.getCell((int) (((fCharacterX+sprChar.getWidth()) / nTileWidth)), (int) (((fCharacterY + sprChar.getHeight() / 2) / nTileHeight)))!=null) {
            System.out.println("right");
            bCollidedX = map.collisionLayer.getCell((int) (((fCharacterX+sprChar.getWidth())/nTileWidth)), (int) (fCharacterY+sprChar.getWidth()/2)/nTileHeight).getTile().getProperties().containsKey("Block");
           // bCollidedX = map.collisionLayer.getCell(((int) ((fCharacterX + sprChar.getWidth() / nTileWidth))), ((int) (((fCharacterY + sprChar.getWidth() / 2) / nTileHeight)))).getTile().getProperties().containsKey("Block");
        }*/
        if(map.collisionLayer.getCell((int) ((fCharacterX+sprChar.getWidth()/2)/nTileWidth), (int) (16-(fCharacterY/nTileHeight)))!=null) {//bottomcollision
            bCollidedY = map.collisionLayer.getCell((int) ((fCharacterX + sprChar.getWidth() / 2) / nTileWidth), (int) (16-(fCharacterY / nTileHeight))).getTile().getProperties().containsKey("Block");
        }
        if(map.collisionLayer.getCell((int) ((fCharacterX+sprChar.getWidth()/2)/nTileWidth), (int) ((fCharacterY + sprChar.getHeight() / 2) / nTileHeight))!=null) {
            System.out.println("top");
            bCollidedY = map.collisionLayer.getCell((int) ((fCharacterX + sprChar.getWidth() / 2) / nTileWidth), (int) ((fCharacterY + sprChar.getHeight() / 2) / nTileHeight)).getTile().getProperties().containsKey("Block");
         //   System.out.println("X: "+(int)((fCharacterX + sprChar.getWidth() / 2) / nTileWidth) + " Y: "+(int)(16-((fCharacterY + sprChar.getHeight() / 2) / nTileHeight)));
        }
           // }
        //}

        if (bCollidedX || bCollidedY) {
            fCharacterX = fOldX;
            fCharacterY = fOldY;
            System.out.println("collision");
        }

        batch.begin();
        batch.draw(sprChar, fCharacterX, fCharacterY);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        sprChar.getTexture().dispose();
    }

    public void getBoolsBack(boolean[] _arbDirection, boolean _bStop, int _nCurrentIndex) {
        arbDirection = _arbDirection;
        bStop = _bStop;
        nCurrentIndex = _nCurrentIndex;
    }
}
