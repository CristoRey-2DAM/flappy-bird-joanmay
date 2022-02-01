/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author joanm
 */
public class GameScreen implements Screen {
    
    int score;
    float distancia;
    FlappyBird game;
    Stage stage;
    TiledMap map;
    GlyphLayout glyphLayout = new GlyphLayout();
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Bird pajaro;

    public GameScreen(FlappyBird game) {
        this.score = 0;
        this.distancia = 60;
        this.game = game;
        map = new TmxMapLoader().load("juego.tmx");
        final float pixelsPerTile = 16;
        renderer = new OrthogonalTiledMapRenderer(map, 1 / pixelsPerTile);
        camera = new OrthographicCamera();

        stage = new Stage();
        stage.getViewport().setCamera(camera);
    }
    
    @Override
    public void show() {
        pajaro = new Bird();
        pajaro.layer = (TiledMapTileLayer) map.getLayers().get("Colisiones");
        pajaro.setPosition(30, 15);
        stage.addActor(pajaro);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (pajaro.gameover && pajaro.yVelocity == 0 && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            pajaro.gameover = false;
            score = 0;
            distancia = 60;
            pajaro.setPosition(30, 15);
            camera.position.x = pajaro.getX();
            camera.update();
        }
        
        camera.position.x = pajaro.getX();
        camera.update();

        renderer.setView(camera);
        renderer.render();
        
        game.batch.begin();
	Assets.font.draw(game.batch, "SCORE " + score, 16, 480 - 20);
        
        if(pajaro.getX() >= distancia && pajaro.getX() <= (distancia + 0.2)) {
            score++;
            distancia = distancia + 10;
        }
        
        if (pajaro.gameover) {
            game.batch.draw(Assets.fin_de_la_partida, 288/3, 512/3, 288/3, 512/3);
        }
        game.batch.end();
        
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, 32 * width / height, 32);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
