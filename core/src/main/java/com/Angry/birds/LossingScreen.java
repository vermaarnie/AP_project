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

public class LossingScreen implements Screen {
    private Game game;
    private Screen previousLevel;
    private Texture loss_screen;
    private SpriteBatch batch;
    private Stage stg;
    private Skin skin;

    public LossingScreen(Game game, Screen previousLevel) {
        this.game = game;
        this.previousLevel = previousLevel;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stg = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stg);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        loss_screen = new Texture(Gdx.files.internal("loss.png"));


        TextButton tryAgain = new TextButton("Try Again", skin);
        tryAgain.setPosition((float) Gdx.graphics.getWidth() / 2 - 50, (float) Gdx.graphics.getHeight() / 2 + 50);
        tryAgain.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelScreen(game));
            }
        });



        stg.addActor(tryAgain);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.begin();
        batch.draw(loss_screen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stg.act(delta);
        stg.draw();
    }

    @Override
    public void resize(int width, int height) {
        stg.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        loss_screen.dispose();
        batch.dispose();
        skin.dispose();
        stg.dispose();
    }
}

