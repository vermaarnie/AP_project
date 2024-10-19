package com.Angry.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class LevelScreen implements Screen {
    private Game game;
    private Texture level_back_ground;
    private SpriteBatch batch;
    private Stage stg;
    private Skin skin;





    public LevelScreen(Game game){
        this.game = game;
    }
    @Override
    public void show() {
        stg = new Stage();
        Gdx.input.setInputProcessor(stg);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton button1 = new TextButton("New Game",skin);
        TextButton button2 = new TextButton("Continue Game",skin);
        TextButton button3 = new TextButton("Exit Game",skin);

        Table tbl = new Table();
        tbl.center();
        tbl.setFillParent(true);

        tbl.add(button1).padBottom(20).row();
        tbl.add(button2).padBottom(20).row();
        tbl.add(button3).padBottom(20).row();

        stg.addActor(tbl);


        level_back_ground = new Texture(Gdx.files.internal("background.jpg"));
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        batch.begin();
        batch.draw(level_back_ground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        batch.end();

        stg.act(delta);
        stg.draw();

    }

    @Override
    public void resize(int width, int height) {
        stg.getViewport().update(width,height,true);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();

    }

    @Override
    public void dispose() {
        stg.dispose();
        skin.dispose();
        level_back_ground.dispose();
        batch.dispose();

    }
}
