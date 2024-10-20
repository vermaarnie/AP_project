package com.Angry.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Level1Screen implements Screen {

    private Texture level_background;
    private Texture ground;

    private Stage stg;
    private Game game;
    private SpriteBatch batch;
    private Texture catp_1;
    private Texture catp_2;
    private Skin skin;
    boolean gameState;

    public Level1Screen(Game game){
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        catp_1 = new Texture(Gdx.files.internal("catp1.png"));
        catp_2 = new Texture(Gdx.files.internal("catp2.png"));


        level_background = new Texture(Gdx.files.internal("level_background.png"));
        ground = new Texture(Gdx.files.internal("ground.png"));
        stg = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stg);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton pause_button = new TextButton("Pause",skin);
        pause_button.setPosition(10,Gdx.graphics.getHeight() - 50);
        pause_button.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y){
                to_pausescreen();

            }
        });

        stg.addActor(pause_button);
    }


    public void to_pausescreen(){
        game.setScreen(new PauseScreen(game,Level1Screen.this));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);

        batch.begin();
        batch.draw(level_background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(ground,0,0,Gdx.graphics.getWidth(),100);
        batch.draw(catp_1,100,80);
        batch.draw(catp_2,100,120);
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

    }

    @Override
    public void dispose() {
        catp_1.dispose();
        catp_2.dispose();

        batch.dispose();
        level_background.dispose();
        ground.dispose();

        stg.dispose();
        skin.dispose();

    }
}
