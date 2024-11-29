package com.Angry.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.bullet.collision._btMprSimplex_t;
import org.w3c.dom.Text;

import java.nio.channels.spi.SelectorProvider;
import java.security.PrivateKey;

public class loadingScreen implements Screen {
    private Game game;
    private SpriteBatch batch;
    Texture loading_screen;

    private float timer;

    public loadingScreen(Game game){
        this.game = game;
        batch = new SpriteBatch();
        loading_screen = new Texture(Gdx.files.internal("loading.png"));


        timer = 0;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        timer = timer + delta;

        Gdx.gl.glClearColor(0,0,0,1);

        batch.begin();
        batch.draw(loading_screen,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();


        if(timer >= 1){
            game.setScreen(new HomeScreen(game));
        }


    }

    @Override
    public void resize(int width, int height) {

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
        loading_screen.dispose();
        batch.dispose();


    }
}

