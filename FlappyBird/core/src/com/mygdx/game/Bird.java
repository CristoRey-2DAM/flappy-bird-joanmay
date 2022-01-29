/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author joanm
 */
public class Bird extends Image {

    Animation fly;

    float time = 0;
    float xVelocity = 0;
    float yVelocity = 0;
    boolean canJump = false;
    boolean isFacingRight = true;
    boolean gameover = false;
    TiledMapTileLayer layer;
    Moneda moneda;

    final float GRAVITY = -1.75f;
    final float VELOCITY = 6f;
    final float DAMPING = 0.87f;

    public Bird() {
        final float width = 18;
        final float height = 26;
        this.setSize(1, height / width);

        fly = new Animation(0.15f, Assets.pajaro_abajo, Assets.pajaro_medio, Assets.pajaro_arriba);
        fly.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    @Override
    public void act(float delta) {
        time = time + delta;
        xVelocity = VELOCITY;
        isFacingRight = true;

        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) && !gameover) {
            yVelocity = VELOCITY * 4;
        }

        yVelocity = yVelocity + GRAVITY;

        float x = this.getX();
        float y = this.getY();
        float xChange = xVelocity * delta;
        float yChange = yVelocity * delta;

        if (canMoveTo(x + xChange, y) == false) {
            xVelocity = xChange = 0;
            yVelocity = yChange = 0;
            gameover = true;
        }

        if (canMoveTo(x, y + yChange) == false) {
            canJump = yVelocity < 0;
            yVelocity = yChange = 0;
            xVelocity = xChange = 0;
            gameover = true;
        }

        this.setPosition(x + xChange, y + yChange);

        xVelocity = xVelocity * DAMPING;
        if (Math.abs(xVelocity) < 0.5f) {
            xVelocity = 0;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame = Assets.pajaro_abajo;

        if (xVelocity != 0) {
            frame = (TextureRegion) fly.getKeyFrame(time);
        }

        batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    private boolean canMoveTo(float startX, float startY) {
        float endX = startX + this.getWidth();
        float endY = startY + this.getHeight();

        int x = (int) startX;
        while (x < endX) {

            int y = (int) startY;
            while (y < endY) {
                if (layer.getCell(x, y) != null) {
                    return false;
                }
                y = y + 1;
            }
            x = x + 1;
        }

        return true;
    }
}
