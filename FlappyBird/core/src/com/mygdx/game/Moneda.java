package com.mygdx.game;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Moneda extends Image {
    TextureRegion stand;
    Animation rotate;

    float time = 0;
    float xVelocity = 0;
    float yVelocity = 0;
    boolean canJump = false;
    boolean isFacingRight = true;
    TiledMapTileLayer layer;

    final float GRAVITY = -2.5f;
    final float MAX_VELOCITY = 10f;
    final float DAMPING = 0.87f;

    public Moneda() {
        final float width = 16;
        final float height = 16;
        this.setSize(1, height / width);

        Texture monedaTexture = new Texture("Coins.png");
        TextureRegion[][] grid = TextureRegion.split(monedaTexture, (int) width, (int) height);

        stand = grid[0][0];
        rotate = new Animation(0.15f, grid[0][1], grid[0][2], grid[0][3]);
        rotate.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame;
        frame = (TextureRegion) rotate.getKeyFrame(time);
        batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
