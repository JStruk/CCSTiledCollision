package com.mygdx.game;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Thumbstick implements ApplicationListener {
    Stage stage;
    public Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin skTouchPad;
    private Drawable drwTouchbg, drwTouchpad;
    Sprite sprFG, sprBG;
    Character character;
    boolean[] arbDirections = new boolean[4];
    boolean bStop;
    private int nCurrentIndex;
    float fTouchPadHeight;

    public void setCharacter(Character _character, boolean[] _arbDirs, boolean _bStop) {
        character = _character;
        arbDirections=_arbDirs;
        bStop = _bStop;
    }

    @Override
    public void create() {
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        //Create a skin for the touchpad
        skTouchPad = new Skin();
        //Set background and knob imgs
        sprFG = new Sprite(new Texture("ThumbstickFGsmall.png"));
        sprBG = new Sprite(new Texture("ThumbstickBG.png"));
        skTouchPad.add("drwTouchbg", sprBG);
        skTouchPad.add("drwTouchpad", sprFG);
        touchpadStyle = new Touchpad.TouchpadStyle();
        //make drawables based off the skin
        drwTouchbg = skTouchPad.getDrawable("drwTouchbg");
        drwTouchpad = skTouchPad.getDrawable("drwTouchpad");
        //Apply the bg and knob drawables to the touchpad
        touchpadStyle.background = drwTouchbg;
        touchpadStyle.knob = drwTouchpad;

        touchpad = new Touchpad(10, touchpadStyle);
        //Initiate the touchpad based on the style we just created
        touchpad.setBounds(0, 0, 100, 100);

        //set where the touchpad will be on the screen
        stage = new Stage();
        //create the stage and add the touchpad to it

        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);
        fTouchPadHeight = sprFG.getHeight()*2;
        //create our crappy rect that is going to move around with the touchpad
        //  sprRect.setPosition(Gdx.graphics.getWidth()/2-sprRect.getWidth()/2, Gdx.graphics.getHeight()/2-sprRect.getHeight()/2);
    }

    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void render() {
        if (touchpad.getKnobPercentX() > .75) {//up,down,right,left
            character.setCharacterVelocity(1, 0);
            arbDirections[3]=true;
            nCurrentIndex=3;
            bStop=false;
        } else if (touchpad.getKnobPercentX() < -.75) {
            character.setCharacterVelocity(-1, 0);
            arbDirections[2]=true;
            nCurrentIndex=2;
            bStop=false;
        } else if (touchpad.getKnobPercentY() > .75) {
            character.setCharacterVelocity(0, 1);
            arbDirections[0]=true;
            nCurrentIndex=0;
            bStop=false;
        } else if (touchpad.getKnobPercentY() < -.75) {
            character.setCharacterVelocity(0, -1);
            arbDirections[1]=true;
            nCurrentIndex=1;
            bStop=false;
        }
        if (touchpad.getKnobPercentY() > -.75 && touchpad.getKnobPercentY() < .75 && touchpad.getKnobPercentX() > -.75 && touchpad.getKnobPercentX() < .75) {
            character.setCharacterVelocity(0, 0);
            bStop=true;
        }
        character.getBoolsBack(arbDirections,bStop, nCurrentIndex);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    }
}
