package com.Angry.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseScreen implements Screen {

    private Game game;
    private Stage stg;
    private Skin skin;
    private Screen prev_screen;

    public PauseScreen(Game game,Screen prev_screen){
        this.game = game;
        this.prev_screen = prev_screen;
    }

    @Override
    public void show() {
        stg = new Stage();
        Gdx.input.setInputProcessor(stg);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton resume = new TextButton("Resume",skin);
        TextButton exit = new TextButton("Exit to levels",skin);

        resume.setPosition(Gdx.graphics.getWidth()/2 - 50,Gdx.graphics.getHeight()/2 + 50);
        resume.addListener(new ClickListener(){
            public void clicked(InputEvent event ,float x,float y){
                game.setScreen(prev_screen);
            }
        });

        exit.setPosition(Gdx.graphics.getWidth()/2 - 50,Gdx.graphics.getHeight()/2 - 50);
        exit.addListener(new ClickListener(){
            public void clicked(InputEvent event ,float x,float y){
                game.setScreen(new LevelScreen(game));
            }
        });

        stg.addActor(resume);
        stg.addActor(exit);





    }

    @Override
    public void render(float delta) {
        stg.act(delta);
        stg.draw();

    }

    @Override
    public void resize(int width, int height) {
        stg.getViewport().update(width, height,true);

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
        stg.dispose();
        skin.dispose();

    }
}
