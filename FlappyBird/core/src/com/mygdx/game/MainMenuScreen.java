/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author joanm
 */
public class MainMenuScreen extends ScreenAdapter {
    
    FlappyBird game;
    OrthographicCamera guiCam;
    Rectangle playBounds;
    Vector3 touchPoint;
    
    public MainMenuScreen (FlappyBird game) {
        this.game = game;
        
	guiCam = new OrthographicCamera(288, 512);
	guiCam.position.set(288 / 2, 512 / 2, 0);
    }
    
    public void update () {
	if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
        }
    }

    public void draw () {
	GL20 gl = Gdx.gl;
	gl.glClearColor(1, 0, 0, 1);
	gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	guiCam.update();
	game.batch.setProjectionMatrix(guiCam.combined);

        game.batch.disableBlending();
	game.batch.begin();
	game.batch.draw(Assets.fondo, 0, 0, 288, 512);
	game.batch.end();
        
	game.batch.enableBlending();
	game.batch.begin();
	game.batch.draw(Assets.menu_principal, 288/4, 512/4, 288/2, 512/2);
	game.batch.end();	
    }

    @Override
    public void render (float delta) {
	update();
	draw();
    }

    @Override
    public void pause () {
	//Settings.save();
    }
}