package com.Angry.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.*;

public class LevelScreen implements Screen {
    private Game game;
    private Texture level_back_ground;
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;

    public LevelScreen(Game game){
        this.game=game;
    }
    @Override
    public void show() {
        stage=new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin=new Skin(Gdx.files.internal("uiskin.json"));

        TextButton level1=new TextButton("Level 1",skin);
        TextButton level2=new TextButton("Level 2",skin);
        TextButton level3=new TextButton("Level 3",skin);


        TextButton to_home = new TextButton("Back",skin);
        to_home.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                to_homescreen();
            }
        });
        level1.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y){
                to_level1screen();
            }
        });

        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.add(level1).padBottom(20).row();
        table.add(level2).padBottom(20).row();
        table.add(level3).padBottom(20).row();
        table.add(to_home).padTop(10).padLeft(10);

        stage.addActor(table);

        level_back_ground=new Texture(Gdx.files.internal("background.jpg"));
        batch=new SpriteBatch();
    }

    private void to_homescreen(){
        game.setScreen(new HomeScreen(game));
    }

    private void to_level1screen(){
        game.setScreen(new Level1Screen(game));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(level_back_ground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);

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
