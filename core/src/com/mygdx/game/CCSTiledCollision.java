package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class CCSTiledCollision extends ApplicationAdapter {
	Map map;
	Thumbstick thumbstick;
	Character character;

	@Override
	public void create() {
		thumbstick = new Thumbstick();
		map = new Map();
		thumbstick.create();
		map.ThumbstickHeight(thumbstick.fTouchPadHeight);
		map.create();
		character = new Character();
		character.setMap(map);
		character.create();
		thumbstick.setCharacter(character, character.arbDirection, character.bStop);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		map.render();
		thumbstick.render();
		character.render();
	}
}
