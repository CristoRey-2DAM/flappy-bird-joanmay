package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends Game {
	public SpriteBatch batch;
        
        public static Texture loadTexture (String file) {
            return new Texture(Gdx.files.internal(file));
	}
        
	@Override
	public void create () {
            batch = new SpriteBatch();
            Assets.load();
            setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
            super.render();
	}
}
