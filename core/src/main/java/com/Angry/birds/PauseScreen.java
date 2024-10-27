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
        TextButton restart = new TextButton("Restart",skin);
        TextButton exit = new TextButton("Exit to levels",skin);


        resume.setPosition((float)Gdx.graphics.getWidth()/2 - 50,(float)Gdx.graphics.getHeight()/2 + 50);
        resume.addListener(new ClickListener(){
            public void clicked(InputEvent event ,float x,float y){
                game.setScreen(prev_screen);
            }
        });

        restart.setPosition((float)Gdx.graphics.getWidth()/2 - 50 ,(float)Gdx.graphics.getHeight()/2);
        restart.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y){
                game.setScreen(new Level1Screen(game));
            }
        });

        exit.setPosition((float)Gdx.graphics.getWidth()/2 - 50,(float)Gdx.graphics.getHeight()/2 - 50);
        exit.addListener(new ClickListener(){
            public void clicked(InputEvent event ,float x,float y){
                game.setScreen(new LevelScreen(game));
            }
        });

        stg.addActor(resume);
        stg.addActor(restart);
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
        prev_screen.dispose();
        stg.dispose();
        skin.dispose();

    }
}
