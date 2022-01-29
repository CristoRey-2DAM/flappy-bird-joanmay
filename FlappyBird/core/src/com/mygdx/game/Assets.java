/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author joanm
 */
public class Assets {
        public static Texture background;
        public static Texture menu;
        public static Texture bird_down;
        public static Texture bird_middle;
        public static Texture bird_up;
        public static Texture gameover;
        public static TextureRegion fondo;
        public static TextureRegion menu_principal;
        public static TextureRegion pajaro_abajo;
        public static TextureRegion pajaro_medio;
        public static TextureRegion pajaro_arriba;
        public static TextureRegion fin_de_la_partida;
        public static BitmapFont font;
        
        public static Texture loadTexture (String file) {
            return new Texture(Gdx.files.internal(file));
	}
        
        public static void load() {    
            
            background = loadTexture("background-day.png");
            menu = loadTexture("message.png");
            bird_down = loadTexture("yellowbird-downflap.png");
            bird_middle = loadTexture("yellowbird-midflap.png");
            bird_up = loadTexture("yellowbird-upflap.png");
            gameover = loadTexture("gameover.png");
            fondo = new TextureRegion(background, 0, 0, 288, 512);
            menu_principal = new TextureRegion(menu, 0, 0, 184, 267);
            pajaro_abajo = new TextureRegion(bird_down, 0, 0, 34, 24);
            pajaro_medio = new TextureRegion(bird_middle, 0, 0, 34, 24);
            pajaro_arriba = new TextureRegion(bird_up, 0, 0, 34, 24);
            fin_de_la_partida = new TextureRegion(gameover, 0, 0, 192, 42);
            font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
        }
}
