package com.Angry.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelScreen implements Screen {
    private Game game;
    private Texture level_back_ground;
    private SpriteBatch batch;


    public LevelScreen(Game game){
        this.game = game;
    }
    @Override
    public void show() {



        level_back_ground = new Texture(Gdx.files.internal("background.png"));
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        batch.begin();
        batch.draw(level_back_ground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

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

    }
}
