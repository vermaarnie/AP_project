package com.Angry.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
public class PauseScreen implements Screen {

    private Game game;
    private Stage stage;
    private Skin skin;
    private Screen previousScreen;

    public PauseScreen(Game game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton resumeButton = new TextButton("Resume", skin);
        TextButton restartButton = new TextButton("Restart", skin);
        TextButton exitButton = new TextButton("Exit to Levels", skin);

        float centerX = Gdx.graphics.getWidth() / 2f - 50;
        resumeButton.setPosition(centerX, Gdx.graphics.getHeight() / 2f + 50);
        restartButton.setPosition(centerX, Gdx.graphics.getHeight() / 2f);
        exitButton.setPosition(centerX, Gdx.graphics.getHeight() / 2f - 50);

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(previousScreen);
            }
        });
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Restart based on the level screen type
                if (previousScreen instanceof LevelScreen1) {
                    game.setScreen(new LevelScreen1(game));
                } else if (previousScreen instanceof LevelScreen2) {
                    game.setScreen(new LevelScreen2(game));
                } else if (previousScreen instanceof LevelScreen3) {
                    game.setScreen(new LevelScreen3(game));
                }  else if (previousScreen instanceof RandomLevel_1) {
                    game.setScreen(new RandomLevel_1(game));
                } else if (previousScreen instanceof RandomLevel_2) {
                    game.setScreen(new RandomLevel_2(game));
                }
                else if (previousScreen instanceof RandomLevel_3) {
                    game.setScreen(new RandomLevel_3(game));
                } else if (previousScreen instanceof RandomLevel_4) {
                    game.setScreen(new RandomLevel_4(game));
                } else if (previousScreen instanceof RandomLevel_5) {
                    game.setScreen(new RandomLevel_5(game));
                }
                else {
                    System.err.println("Restart is not supported for this screen.");
                }
            }
        });



        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelScreen(game));
            }
        });
        System.out.println("PauseScreen shown");


        stage.addActor(resumeButton);
        stage.addActor(restartButton);
        stage.addActor(exitButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

        stage.dispose();
        skin.dispose();
    }
}

